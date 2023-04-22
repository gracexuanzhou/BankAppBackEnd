package nl.rabobank.service;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.BankAccountRepository;
import nl.rabobank.repository.CustomerRepository;
import nl.rabobank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BankAccountServiceImp implements BankAccountService {

    @Autowired
    BankAccountRepository bankAccoutRepository;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public BankAccount createBankAccount(BankAccount bankAccount){
        if(bankAccount.getBankAccountId() != null){
            throw new InvalidPropertyState("id does not have to be set for new bank Account");
           }
        return bankAccoutRepository.save(bankAccount);
        }

    @Override
    public void update(BankAccount bankAccount) {
        if (bankAccount.getBankAccountId() == null) {
            throw new InvalidPropertyState("id has to be set");
        }
        bankAccoutRepository.save(bankAccount);
    }

    @Override
    public Optional<BankAccount> findById(Long bankAccountID) {
        return bankAccoutRepository.findById(bankAccountID);
    }

    @Override
    public void deleteById(Long bankAccountId) {
        bankAccoutRepository.deleteById(bankAccountId);
    }

    @Override
    public List<BankAccount> findAll() {
        return bankAccoutRepository.findAll();
    }

    @Override
    public BankAccountRepository getBankAccoutRepository() {
        return bankAccoutRepository;
    }

    @Override
    public void setBankAccoutRepository(BankAccountRepository bankAccoutRepository) {
        this.bankAccoutRepository = bankAccoutRepository;
    }


    public BankAccount getBankAccountById(Long bankAccountId){
        return bankAccoutRepository.findById(bankAccountId)
                .orElseThrow(() -> new InvalidPropertyState("bankAccount id not find"));
    }


    public List<BankAccount> findAllBankAccountByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new InvalidPropertyState("Customer id not find"));
        return bankAccoutRepository.findAllBankAccountByCustomerId(customer.getCustomerId());
    }



}

