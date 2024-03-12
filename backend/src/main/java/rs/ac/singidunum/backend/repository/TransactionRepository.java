package rs.ac.singidunum.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import rs.ac.singidunum.backend.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
}
