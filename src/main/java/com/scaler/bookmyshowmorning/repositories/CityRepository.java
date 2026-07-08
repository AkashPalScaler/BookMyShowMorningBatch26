package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.City;
import com.scaler.bookmyshowmorning.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
}
