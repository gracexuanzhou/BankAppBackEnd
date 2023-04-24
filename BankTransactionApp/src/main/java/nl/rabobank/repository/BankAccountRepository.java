package nl.rabobank.repository;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount,Long> {

    //BankAccount save(BankAccount bankAccount);

   // Optional<BankAccount> findById(Long bankAccountId);

    //void deleteById(Long bankAccountId);

    //List<BankAccount> findAll();
    List<BankAccount> findAllBankAccountByCustomerId(Long customerID);

}
