package rs.ac.singidunum.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.backend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);
}
