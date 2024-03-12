package rs.ac.singidunum.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.Bike;
import rs.ac.singidunum.backend.entity.Transaction;
import rs.ac.singidunum.backend.entity.User;
import rs.ac.singidunum.backend.repository.BikeRepository;
import rs.ac.singidunum.backend.repository.UserRepository;
import rs.ac.singidunum.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BikeRepository bikeRepository;

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction) {
        User buyer = userRepository.findById(transaction.getUserId()).orElse(null);
        Bike product = bikeRepository.findById(transaction.getBikeId()).orElse(null);

        if (buyer == null || product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        transaction.setBuyer(buyer);
        transaction.setProduct(product);

        return ResponseEntity.ok(service.saveTransaction(transaction));
    }

    @GetMapping("/transactions")
    public List<Transaction> findAllTransactions() {
        return service.getTransactions();
    }

    @GetMapping("/transaction/id/{id}")
    public Transaction findTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }
}
