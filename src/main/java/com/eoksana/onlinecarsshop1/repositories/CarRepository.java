package com.eoksana.onlinecarsshop1.repositories;

import com.eoksana.onlinecarsshop1.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByModell(String modell);
    List<Car> findAllBySoldFalse();
    List<Car> findAllByDeletedFalse();
    List<Car> findAllByDeletedFalseAndSoldFalse();

}
