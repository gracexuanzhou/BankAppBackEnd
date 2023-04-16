package nl.rabobank.controller;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import nl.rabobank.service.BankAccountService;
import nl.rabobank.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/bankaccount")
public class BankAccountController {

    @Autowired
    CustomerService customerService;

    @Autowired
    BankAccountService bankAccountService;

    @GetMapping
    public List<BankAccount> findAll() {
        return bankAccountService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BankAccount> findById(@PathVariable Long id) {
        Optional<BankAccount> bankAccountOption = bankAccountService.findById(id);
        if (bankAccountOption.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        BankAccount bankAccount = bankAccountOption.get();
        return new ResponseEntity<>(bankAccount, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankAccount> createBankAccount(@RequestBody BankAccount bankAccount) {
        return new ResponseEntity<>(bankAccountService.createBankAccount(bankAccount), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<BankAccount> updateBankAccount(@RequestBody BankAccount bankAccount) {
        bankAccountService.update(bankAccount);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BankAccount> deleteById(@PathVariable Long id) {
        bankAccountService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/customer/{customerId}")
    public List<BankAccount> findAllByCustomerId(@PathVariable Long customerId) {
        return bankAccountService.findAllBankAccountByCustomerId(customerId);
    }


    @ExceptionHandler(InvalidPropertyState.class)
    public ResponseEntity<Map<String, String>> handleException(Exception ex) {
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
