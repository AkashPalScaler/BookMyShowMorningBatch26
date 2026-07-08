package com.scaler.bookmyshowmorning.repositories;

import com.scaler.bookmyshowmorning.models.Screen;
import com.scaler.bookmyshowmorning.models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Long> {
}
