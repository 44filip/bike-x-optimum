package rs.ac.singidunum.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.backend.entity.Bike;

public interface BikeRepository extends JpaRepository<Bike, Integer> {
    Bike findByModel(String model);
}
