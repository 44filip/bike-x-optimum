package rs.ac.singidunum.backend.controller;

import org.springframework.web.bind.annotation.*;
import rs.ac.singidunum.backend.entity.Transaction;
import rs.ac.singidunum.backend.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/transactions")
    public List<Transaction> findAllTransactions() {
        return service.getTransactions();
    }

    @GetMapping("/transaction/{id}")
    public Transaction findTransactionById(@PathVariable int id) {
        return service.getTransactionById(id);
    }
}