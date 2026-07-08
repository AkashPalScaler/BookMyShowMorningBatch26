package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.Screen;
import com.scaler.bookmyshowmorning.models.Seat;
import com.scaler.bookmyshowmorning.models.SeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long> {
}
