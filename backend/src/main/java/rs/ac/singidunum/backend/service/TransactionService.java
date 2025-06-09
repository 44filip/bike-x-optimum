package rs.ac.singidunum.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.ac.singidunum.backend.entity.Bike;
import rs.ac.singidunum.backend.entity.Transaction;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.repository.BikeRepository;
import rs.ac.singidunum.backend.repository.TransactionRepository;
import rs.ac.singidunum.backend.repository.UserRepository;

import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BikeRepository bikeRepository;

    public Transaction saveTransaction(Transaction transaction) {
        User buyer = userRepository.findById(transaction.getUserId()).orElse(null);
        Bike product = bikeRepository.findById(transaction.getBikeId()).orElse(null);

        transaction.setBuyer(buyer);
        transaction.setProduct(product);

        return repository.save(transaction);
    }

    public List<Transaction> getTransactions() {
        return repository.findAll();
    }

    public Transaction getTransactionById(int id) {
        return repository.findById(id).orElse(null);
    }
}