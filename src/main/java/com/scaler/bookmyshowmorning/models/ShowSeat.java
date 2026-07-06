package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
public class ShowSeat extends BaseModel {
    @ManyToOne
    private Show show;
    @ManyToOne
    private  Seat seat;
    @Enumerated(EnumType.STRING)
    private ShowSeatStatus showSeatStatus;
    @ManyToOne
    private Booking booking;
    private Date blockedAt;
}











// Show seat represents virtual seat you are booking, but 1 physical can be in many shows
// ShowSeat : Seat
// 11AM_H1  :  H1
// 11AM_H2  :  H2
// 12AM_H1  :  H1
// 12AM_H2  :  H2

