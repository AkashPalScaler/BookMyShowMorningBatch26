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
    private ResponseStatus responseStatus;

    public Long getBookingId() {
        return bookingId;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BookingStatus getStatus() {
        return status;
    }

    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
