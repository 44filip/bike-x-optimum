package rs.ac.singidunum.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.backend.entity.Transaction;
import rs.ac.singidunum.backend.repository.TransactionRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(int id) {
        return repository.findById(id).orElse(null);
    }
}