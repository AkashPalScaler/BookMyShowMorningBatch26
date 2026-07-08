package com.scaler.bookmyshowmorning.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.jmx.export.annotation.ManagedNotification;

import java.util.List;

@Getter
@Setter
@Entity
public class Screen extends BaseModel{
    private String name;
    @Enumerated(EnumType.STRING)
    @ElementCollection
    private List<Feature> features;
    // Screen 1:M Seat
    @OneToMany(mappedBy = "screen")
    private List<Seat> seats; // Physical seats
    // Screen M:1 Theatre
    @ManyToOne
    private Theatre theatre;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Feature> getFeatures() {
        return features;
    }

    public void setFeatures(List<Feature> features) {
        this.features = features;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
