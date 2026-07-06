package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShowRepository extends JpaRepository<Show, Long> {
}
