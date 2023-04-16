package nl.rabobank.repository;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepository extends JpaRepository<Transaction,Long> {

    //Transaction save(Transaction transaction);

    Optional<Transaction> findById(Long transactionId);

    void deleteById(Long transactionId);

    List<Transaction> findAll();
}
