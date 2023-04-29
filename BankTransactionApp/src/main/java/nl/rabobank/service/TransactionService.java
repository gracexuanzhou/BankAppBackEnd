package nl.rabobank.service;

import nl.rabobank.model.Transaction;
import nl.rabobank.repository.TransactionRepository;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    void update(Transaction transaction);

    Optional<Transaction> findById(Long transactionID);

    void deleteById(Long transactionID);

    List<Transaction> findAll();

    TransactionRepository getTransactionRepository();

    void setTransactionRepository(TransactionRepository transactionRepository);

    List<Transaction> findAllTransactionByBankAccountId(Long bankAccountId);

   // @Transactional
    //void updateCo2Value(Long customerId, Long categoryId, BigDecimal newCo2Value);
}
