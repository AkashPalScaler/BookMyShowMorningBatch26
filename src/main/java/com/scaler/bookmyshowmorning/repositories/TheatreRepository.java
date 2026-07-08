package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.City;
import com.scaler.bookmyshowmorning.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Long> {
}
