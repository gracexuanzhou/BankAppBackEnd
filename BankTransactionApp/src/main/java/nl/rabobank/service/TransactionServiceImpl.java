package nl.rabobank.service;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.BankAccountRepository;
import nl.rabobank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    BankAccountService bankAccountService;


    @Override
    public Transaction createTransaction(Transaction transaction){
        if(transaction.getId() != null){
            throw new InvalidPropertyState("id does not have to be set for transaction");
        }
        BankAccount bankAccount = transaction.getBankAccount();
        bankAccountService.addTransactionToBankAccount(bankAccount.getId(),transaction);
        //transaction.getBankAccount().updateBalance(transaction);
        return transactionRepository.save(transaction);
    }
    @Override
    public void update(Transaction transaction) {
        if (transaction.getId() == null) {
            throw new InvalidPropertyState("id has to be set");
        }
        transactionRepository.save(transaction);
    }

    @Override
    public Optional<Transaction> findById(Long transactionID) {
        return transactionRepository.findById(transactionID);
    }

    @Override
    public void deleteById(Long transactionID) {transactionRepository.deleteById(transactionID);
    }

    @Override
    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    @Override
    public TransactionRepository getTransactionRepository() {
        return transactionRepository;
    }

    @Override
    public void setTransactionRepository(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


}

