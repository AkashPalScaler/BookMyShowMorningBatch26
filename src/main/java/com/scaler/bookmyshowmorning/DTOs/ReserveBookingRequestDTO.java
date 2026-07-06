package com.scaler.bookmyshowmorning.DTOs;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ReserveBookingRequestDTO {
    private Long userId;
    private List<Long> showSeatIds;
    private Long showId; // You need to check if all the seats are from the correct show
}
