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
}
