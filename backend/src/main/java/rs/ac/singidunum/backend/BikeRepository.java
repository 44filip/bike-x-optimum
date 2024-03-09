package rs.ac.singidunum.backend;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BikeRepository extends JpaRepository<Bikes, Integer> {
}