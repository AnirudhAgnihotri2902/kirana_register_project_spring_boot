package org.example.kirana_register_project.crud.controller;


import org.example.kirana_register_project.crud.exception.ResourceNotFoundException;
import org.example.kirana_register_project.crud.model.Transactions;
import org.example.kirana_register_project.crud.service.TransactionService;
import org.example.kirana_register_project.crud.types.DateRangeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class TransactionsController {

    @Autowired
    private final TransactionService transactionService;

    public TransactionsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transaction")
    public List<Transactions> createTransaction(@Valid @RequestBody Transactions transactions) {
        return transactionService.createTransaction(transactions);
    }

    @GetMapping("/users/transactions/{id}")
    public List<Transactions> getTransactionsByUserID(@PathVariable(value = "id") UUID userId)
            throws ResourceNotFoundException {
        return transactionService.getTransactionsByUserID(userId);

    }

    @GetMapping("/transactions/{id}")
    public List<Transactions> GetAllTransactionsByTxnID(@PathVariable(value = "id") UUID txnID)
            throws ResourceNotFoundException {
        return transactionService.GetAllTransactionsByTxnID(txnID);

    }

    @GetMapping("/users/transactions/dates")
    public List<Transactions> GetAllTransactionsBetweenDates(@Valid @RequestBody DateRangeRequest request) {
        return transactionService.GetAllTransactionsBetweenDates(request.getStartDate(), request.getEndDate());
    }


}
