package com.scaler.bookmyshowmorning.services;

import com.scaler.bookmyshowmorning.models.*;
import com.scaler.bookmyshowmorning.repositories.ShowRepository;
import com.scaler.bookmyshowmorning.repositories.ShowSeatRepository;
import com.scaler.bookmyshowmorning.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;


    public Booking reserveBooking(Long userId, Long showId, List<Long> showSeatIds){
        // TODO : Business logic for reserving the seats for 5 mins and generate booking

        // Fetch user with userId from userRepository
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isEmpty()){
            throw new IllegalCallerException("Invalid user");
        }
        User user = optionalUser.get();

        // Fetch show with showId from showRepository
        Optional<Show> optionalShow= showRepository.findById(showId);
        if(optionalShow.isEmpty()){
            throw new IllegalArgumentException("Invalid show");
        }
        Show show = optionalShow.get();
        // --------------- START TRANSACTION ------------------
        // Fetch showSeats with showSeatIds from showRepository
        // Check availability of the showSeats
        // If available,
        //          - Lock the seats -  update status(to Locked) and update LockedAt time
        // Else,
        // if Booked - throw error
        // if locked - check the duration of the lock :
        //          (currentTime - lockedAt) > 5 : update the lockedAt time
        //          (currentTime - lockedAt) < 5 : throw error
        // ---------------- STOP TRANSACTION ------------------
        List<ShowSeat> validShowSeats = checkAndReserveSeats(show, showSeatIds);
        // Calculate price - showSeat - seat(SeatType) -> price
            // HomeWork
        Integer amount = 200;
        // Create and return the booking
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setShow(show);
        booking.setBookingStatus(BookingStatus.IN_PROGRESS);
        booking.setShowSeats(validShowSeats);
        booking.setAmount(amount);
        return booking;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    List<ShowSeat> checkAndReserveSeats(Show show, List<Long> showSeatIds){
        // --------------- START TRANSACTION ------------------
        // Fetch showSeats with showSeatIds from showRepository
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);
        // Show and seat validation
        // OPTION 1 - Throw error if seats from multiple shows are selected
//        for(ShowSeat showSeat : showSeats){
//            if(!showSeat.getShow().equals(show)) {
//                throw new IllegalArgumentException("Show seats should all belong to the same show");
//            }
//        }
        // OPTION 2 - Don't throw err but only consider the seats of the show selected
        List<ShowSeat> validShowSeats = new ArrayList<>();
        for(ShowSeat showSeat : showSeats){
            if(showSeat.getShow().equals(show)) {
                 validShowSeats.add(showSeat);
            }
        }
        // Check availability of the showSeats
        // If available,
        //          - Lock the seats -  update status(to Locked) and update LockedAt time
        // Else,
        // if Booked - throw error
        // if locked - check the duration of the lock :
        //          (currentTime - lockedAt) > 5 : update the lockedAt time
        //          (currentTime - lockedAt) < 5 : throw error
        // ---------------- STOP TRANSACTION ------------------
        for(ShowSeat showSeat : validShowSeats){
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BOOKED)){
                throw new IllegalArgumentException("Seats selected are already booked");
            }
            if(showSeat.getShowSeatStatus().equals(ShowSeatStatus.BLOCKED)){
                Long minsSinceBlocked = Duration.between(
                        new Date().toInstant(),
                        showSeat.getBlockedAt().toInstant()
                ).toMinutes();

                if(minsSinceBlocked < 5){
                    throw new IllegalArgumentException("Seats selected are already reserved for booking, try again some time later!");
                }
            }
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
//            showSeatRepository.save(showSeat); // Update one by one

        }
        // Update all the seats together
        showSeatRepository.saveAll(validShowSeats);
        return validShowSeats;
    }
}
// Homework: FetchShowSeats: