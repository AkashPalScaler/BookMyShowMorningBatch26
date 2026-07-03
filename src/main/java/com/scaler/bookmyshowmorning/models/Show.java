package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name="bms_show")
public class Show extends BaseModel {
    private Date startTime;
    private  Date endTime;
    // Show M:1 Movie
    @ManyToOne
    private Movie movie;
    // Show M:1 Theatre
    @ManyToOne
    private Theatre theatre;
    // SHow M:1 Screen
    @ManyToOne
    private Screen screen;
    // Show 1:M ShowSeat
    @OneToMany(mappedBy = "show")
    private List<ShowSeat> showSeatList;
    @OneToMany(mappedBy = "show")
    private List<ShowSeatType> showSeatTypeList;
}


// Show ShowSeat
// 11AM  11AM_H1 - Booked
// 11AM  11AM_H2 - Not Booked
// 11AM  11AM_J1
// 11AM  11AM_J2
// 12AM  12AM_H1
// 12AM  12AM_H2

// Show ShowSeatType
// 11AM   11AM_GOLD - 500
// 11AM   11AM_SILVER -200
// 12PM   12PM_GOLD - 1200
// 12PM   12PM_SILVER - 700
