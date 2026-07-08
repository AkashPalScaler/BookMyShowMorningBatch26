package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "bms_user")
public class User extends BaseModel {
    private String name;
    private String email;
    private String password;
    @OneToMany(mappedBy = "user")
    private List<Booking> bookings;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
    }
}

// User: id(uid1), createdAt, updatedAt, name, email, password |     Not do this -> [b1,b2,b3,b4...]-X

// Instead do this -> mappoing table
// bms_user_bookings : user_id, booking_id
// uid1 , b1
// uid1 , b2
// uid1 , b3
// uid1 , b4
// uid2, b5

// Break till : 8:30 AM