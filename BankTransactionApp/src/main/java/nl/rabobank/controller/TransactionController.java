package nl.rabobank.controller;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import nl.rabobank.model.Transaction;
import nl.rabobank.service.CustomerService;
import nl.rabobank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    TransactionService transactionService;

    @GetMapping
    public List<Transaction> findAll() {
        return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transaction> findById(@PathVariable Long id) {
        Optional<Transaction> transactionOption =transactionService.findById(id);
        if (transactionOption.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Transaction transaction = transactionOption.get();
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody Transaction transaction) {
        return new ResponseEntity<>(transactionService.createTransaction(transaction), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Transaction> updateCustomer(@RequestBody Transaction transaction) {
        transactionService.update(transaction);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Transaction> deleteById(@PathVariable Long id) {
        transactionService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/bankaccount/{bankAccountId}")
    public List<Transaction> findAllTransactionByBankAccountId(@PathVariable Long bankAccountId) {
        return transactionService.findAllTransactionByBankAccountId(bankAccountId);
    }

    @ExceptionHandler(InvalidPropertyState.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}