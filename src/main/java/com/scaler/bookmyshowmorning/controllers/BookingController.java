package com.scaler.bookmyshowmorning.controllers;

import com.scaler.bookmyshowmorning.DTOs.ReserveBookingRequestDTO;
import com.scaler.bookmyshowmorning.DTOs.ReserveBookingResponseDTO;
import com.scaler.bookmyshowmorning.models.Booking;
import com.scaler.bookmyshowmorning.models.BookingStatus;
import com.scaler.bookmyshowmorning.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class BookingController {
    @Autowired
    BookingService bookingService;

    ReserveBookingResponseDTO reserveBooking(ReserveBookingRequestDTO requestDTO){
        ReserveBookingResponseDTO responseDTO = new ReserveBookingResponseDTO();
        try{
            Booking booking = bookingService.reserveBooking(
                    requestDTO.getUserId(),
                    requestDTO.getShowId(),
                    requestDTO.getShowSeatIds()
            );
            responseDTO.setBookingId(booking.getId());
            responseDTO.setStatus(booking.getBookingStatus());
            responseDTO.setMessage("Seats reserved successfully for 5 mins, please complete the payment for confirming the booking");
        }catch (Exception e){
            System.out.println("Error in reserving seats - " + e.getMessage());
            responseDTO.setStatus(BookingStatus.FAILED);
            responseDTO.setMessage("Failed to reserve seats because of " + e.getMessage());
        }
        return responseDTO;
    }
}
