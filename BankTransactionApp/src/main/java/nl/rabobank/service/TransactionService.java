package nl.rabobank.service;

import nl.rabobank.model.Transaction;
import nl.rabobank.repository.TransactionRepository;

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
}
