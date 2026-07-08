package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel {
    // Booking M:1 User
    @ManyToOne
    private User user;
    private Integer amount;
    // Booking 1:M Payment
    @OneToMany(mappedBy = "booking")
    private List<Payment> payments;
    // Booking M:1 Show
    @ManyToOne
    private Show show;
    @OneToMany(mappedBy = "booking")
    private List<ShowSeat> showSeats;
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public void setPayments(List<Payment> payments) {
        this.payments = payments;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<ShowSeat> getShowSeats() {
        return showSeats;
    }

    public void setShowSeats(List<ShowSeat> showSeats) {
        this.showSeats = showSeats;
    }

    public BookingStatus getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(BookingStatus bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}
// Booking : id, createdAt, updatedAt, amount, user_id
// b1, cat, uat, 1000, uid1
// b2, cat, uat, 1000, uid1
// b3, cat, uat, 1000, uid1
// b4, cat, uat, 1000, uid1

// select * from booking where uid="uid1";