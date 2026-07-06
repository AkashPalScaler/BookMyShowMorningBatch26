package com.scaler.bookmyshowmorning.DTOs;

import com.scaler.bookmyshowmorning.models.BookingStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReserveBookingResponseDTO {
    private Long bookingId;
    private String message;
    private BookingStatus status;
    private Integer amount;
}
