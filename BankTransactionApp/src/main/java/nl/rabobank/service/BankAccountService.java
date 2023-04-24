package nl.rabobank.service;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.BankAccountRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountService {
    BankAccount createBankAccount(BankAccount bankAccount);

    void update(BankAccount bankAccount);

    Optional<BankAccount> findById(Long bankAccountID);

    void deleteById(Long customerId);

    List<BankAccount> findAll();

    BankAccountRepository getBankAccoutRepository();

    void setBankAccoutRepository(BankAccountRepository bankAccoutRepository);

    //BankAccount addTransactionToBankAccount(Long bankAccountId, Transaction transaction);

   // BankAccount addBankAccountToCustomer(Long customerId, Long bankAccountId);

    List<BankAccount> findAllBankAccountByCustomerId(Long customerId);

    BigDecimal CountTotalIncomingAmountByCustomerId(Long customerId);
    BigDecimal CountTotalOutgoingAmountByCustomerId(Long customerId);
    BigDecimal CountTotalBalanceByCustomerId(Long customerId);
}
