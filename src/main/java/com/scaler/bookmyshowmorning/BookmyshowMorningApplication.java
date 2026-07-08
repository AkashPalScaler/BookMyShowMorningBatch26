package com.scaler.bookmyshowmorning;

import com.scaler.bookmyshowmorning.DTOs.*;
import com.scaler.bookmyshowmorning.controllers.BookingController;
import com.scaler.bookmyshowmorning.controllers.UserController;
import com.scaler.bookmyshowmorning.models.*;
import com.scaler.bookmyshowmorning.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class BookmyshowMorningApplication implements CommandLineRunner {

    @Autowired // Gets injected from the registry
    MovieRepository movieRepository;
    @Autowired
    UserController userController;
    @Autowired
    BookingController bookingController;
    @Autowired
    ShowRepository showRepository;
    @Autowired
    ShowSeatRepository showSeatRepository;
    @Autowired
    BookingRepository bookingRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    TheatreRepository theatreRepository;
    @Autowired
    ScreenRepository screenRepository;
    @Autowired
    SeatRepository seatRepository;
    @Autowired
    SeatTypeRepository seatTypeRepository;
    @Autowired
    ShowSeatTypeRepository showSeatTypeRepository;

    public static void main(String[] args) {
        SpringApplication.run(BookmyshowMorningApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//        UserSignupRequestDTO requestDTO = new UserSignupRequestDTO();
//        requestDTO.setName("Narasimha");
//        requestDTO.setEmail("narsimha@email.com");
//        requestDTO.setPassword("password");
//
//        UserSignupResponseDTO responseDTO = userController.signup(requestDTO);
//        if(responseDTO.getStatus().equals(ResponseStatus.SUCCESS)){
//            System.out.println(responseDTO.getMessage());
//            System.out.println("User created - " + responseDTO.getUserId());
//        }else{
//            System.out.println(responseDTO.getMessage());
//        }

//        dataSetup();

        ReserveBookingRequestDTO RBrequestDTO = new ReserveBookingRequestDTO();
        RBrequestDTO.setUserId(1L);
        RBrequestDTO.setShowId(1L);
        RBrequestDTO.setShowSeatIds(List.of(1L,2L));
        ReserveBookingResponseDTO rbResponseDTO = bookingController.reserveBooking(RBrequestDTO);
        if(rbResponseDTO.getResponseStatus().equals(ResponseStatus.SUCCESS)){
            System.out.println(rbResponseDTO.getMessage());
            System.out.println("Booking is initiated with " + rbResponseDTO.getBookingId());
            System.out.println("Please pay Rs." + rbResponseDTO.getAmount() + " to confirm your booking");
        }else{
            System.out.println(rbResponseDTO.getMessage());
        }



    }

    void dataSetup(){
        // Create a city
        City city1 = new City();
        city1.setName("Mumbai");
        city1 = cityRepository.save(city1);

        // Create a theatre
        Theatre theatre1 = new Theatre();
        theatre1.setName("Phoenix IMAX");
        theatre1.setCity(city1);
        theatre1 = theatreRepository.save(theatre1);

//        theatre1.setShows();
        // Create a screen
        Screen screen1 = new Screen();
        screen1.setName("Screen 1");
        screen1.setTheatre(theatre1);
        screen1 = screenRepository.save(screen1);

        // CReate the seatTypes
        SeatType gold = new SeatType("GOLD");
        SeatType silver = new SeatType("SILVER");
        List<SeatType> seatTypes = List.of(gold, silver);
        seatTypeRepository.saveAll(seatTypes);
        // Create seats in the screen (Seat Types: GOLD, SILVER)

        Seat seat1 = new Seat(); seat1.setName("1A"); seat1.setSeatType(gold);seat1.setScreen(screen1);
        Seat seat2 = new Seat(); seat2.setName("2A"); seat2.setSeatType(gold);seat2.setScreen(screen1);
        Seat seat3 = new Seat(); seat3.setName("3A"); seat3.setSeatType(silver);seat3.setScreen(screen1);
        Seat seat4 = new Seat(); seat4.setName("4A"); seat4.setSeatType(silver);seat4.setScreen(screen1);
        List<Seat> seats = List.of(seat1, seat2, seat3, seat4);

        seatRepository.saveAll(seats);

        screen1.setSeats(seats);
        screen1 = screenRepository.save(screen1);
        List<Screen> screens = List.of(screen1);
        theatre1.setScreens(screens);
        theatre1 = theatreRepository.save(theatre1);
        List<Theatre> theatres = List.of(theatre1);
        city1.setTheatres(theatres);
        city1 = cityRepository.save(city1);

        // Create a movie
        Movie movie1 = new Movie();
        movie1.setName("Dhurandhar");
        movieRepository.save(movie1);
        // Create a show (9AM show in screen 1)
        Show show1 = new Show();
        show1.setMovie(movie1);
        show1.setScreen(screen1);
        show1 = showRepository.save(show1);
        postShowCreationSetup(show1, seatTypes, seats);

        // Generate show seats when show is added
        // Take price from admin and generate showSeatTypes
    }

    void postShowCreationSetup(Show show, List<SeatType> seatTypes, List<Seat> seats){
        List<ShowSeat> showSeats = new ArrayList<>();
        for(Seat seat : seats){
            ShowSeat showSeat = new ShowSeat();
            showSeat.setShow(show);
            showSeat.setSeat(seat);
            showSeat.setShowSeatStatus(ShowSeatStatus.AVAILABLE);
            showSeats.add(showSeat);
        }
        showSeatRepository.saveAll(showSeats);
        List<ShowSeatType> showSeatTypes = new ArrayList<>();
        for(SeatType seatType: seatTypes){
            ShowSeatType showSeatType = new ShowSeatType();
            showSeatType.setShow(show);
            showSeatType.setSeatType(seatType);
            if(seatType.getType().equals("GOLD")){
                showSeatType.setPrice(300);
            }else{
                showSeatType.setPrice(200);
            }
            showSeatTypes.add(showSeatType);
        }
        showSeatTypeRepository.saveAll(showSeatTypes);
    }

}
//9AM,1A, status(AVAILABLE)
//9AM,2A
//9AM,3A
//9AM,4A

// 9AM, GOLD, price
// 9AM, SILVER