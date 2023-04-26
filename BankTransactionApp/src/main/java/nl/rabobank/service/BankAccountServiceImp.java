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

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
        bankAccount.setIban(generateIban());
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

    public BigDecimal CountTotalIncomingAmountByCustomerId(Long customerId){
        List<BankAccount> bankAccountList = bankAccoutRepository.findAllBankAccountByCustomerId(customerId);
        BigDecimal totalIncomingAmount = BigDecimal.ZERO;
        for (BankAccount bankAccount : bankAccountList) {
            List<Transaction> transactionList = transactionRepository.findAllTransactionByBankAccountId(bankAccount.getBankAccountId());
            for (Transaction transaction : transactionList) {
                if (transaction.getIncomingAmount() != null) {
                    totalIncomingAmount = totalIncomingAmount.add(transaction.getIncomingAmount());
                }
            }
        }
        return totalIncomingAmount;
    }

    public BigDecimal CountTotalOutgoingAmountByCustomerId(Long customerId){
        List<BankAccount> bankAccountList = bankAccoutRepository.findAllBankAccountByCustomerId(customerId);
        BigDecimal totalOutgoingAmount = BigDecimal.ZERO;
        for (BankAccount bankAccount : bankAccountList) {
            List<Transaction> transactionList = transactionRepository.findAllTransactionByBankAccountId(bankAccount.getBankAccountId());
            for (Transaction transaction : transactionList) {
                if (transaction.getOutgoingAmount() != null) {
                    totalOutgoingAmount = totalOutgoingAmount.add(transaction.getOutgoingAmount());
                }
            }
        }
        return totalOutgoingAmount;
    }

    public BigDecimal CountTotalBalanceByCustomerId(Long customerId){
        List<BankAccount> bankAccountList = bankAccoutRepository.findAllBankAccountByCustomerId(customerId);
        BigDecimal balance = BigDecimal.ZERO;
        for (BankAccount bankAccount : bankAccountList) {
           balance = balance.add(bankAccount.getBalance());
        }
        return balance;
    }

    public String generateIban(){
        String iban = "";
        int ibanLength = 7;
        String characterSet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder builder = new StringBuilder();
        builder.append("NLXXXRABO");

        for (int i = 0; i < ibanLength; i++) {
            int randomIndex = random.nextInt(characterSet.length());
            builder.append(characterSet.charAt(randomIndex));
        }
        iban = builder.toString();
        return iban;

    }







}

