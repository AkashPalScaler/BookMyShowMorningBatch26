package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.ShowSeat;
import com.scaler.bookmyshowmorning.models.ShowSeatType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowSeatTypeRepository extends JpaRepository<ShowSeatType, Long> {
}
