package nl.rabobank.service;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Categories;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.BankAccountRepository;
import nl.rabobank.repository.CategoryRepository;
import nl.rabobank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    BankAccountRepository bankAccountRepository;


    @Override
    public Transaction createTransaction(Transaction transaction){
        if(transaction.getId() != null){
            throw new InvalidPropertyState("id does not have to be set for transaction");
        }
        addTransactionToBankAccount(transaction);
        if(transaction.getDescription()!=null){
            categorizeTransaction(transaction);
        }
        else {
            transaction.setCategoryId(8L);
        }
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

    public void categorizeTransaction(Transaction transaction) {
        String description = transaction.getDescription().toLowerCase();

        boolean isTransport = Arrays.asList("car", "ov", "bus","uber","ns","klm","skyscanner").stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isGroceries = Arrays.asList("albert","jumbo","lidl","aldi",
                        "meat","milk","tesco","spar").stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isDining= Arrays.asList("restaurant", "cafe", "fast food","coffee","bakery",
                        "pub","bar","takeaway","bistro","kfc","febo","mcdonald's","starbucks").stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isShopping = Arrays.asList("clothing", "shoes", "fashion","apparel","accessories",
                       "dress","pants","shirt","blouse","jacket","zara","uniqlo","h&m",
                        "c&a","primark","gap","nike","mango","adidas").stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isHousingExpenses = Arrays.asList("rent", "mortgage", "property","maintenance",
                        "utility","internet","cable").stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isEntertainment = Arrays.asList("netflix","movie","spotify","amazon"," ticketmaster",
                       "comedy" ).stream()
                .anyMatch(keyword -> description.contains(keyword));

        boolean isSalary = Arrays.asList("salary").stream()
                .anyMatch(keyword -> description.contains(keyword));

        if (isTransport) {
            Categories category = categoryRepository.findByNameIgnoreCase("transport");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isEntertainment){
            Categories category = categoryRepository.findByNameIgnoreCase("entertainment");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isDining){
            Categories category = categoryRepository.findByNameIgnoreCase("dining");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isGroceries){
            Categories category = categoryRepository.findByNameIgnoreCase("groceries");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isShopping){
            Categories category = categoryRepository.findByNameIgnoreCase("shopping");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isHousingExpenses){
            Categories category = categoryRepository.findByNameIgnoreCase("housing Expenses");
            transaction.setCategoryId(category.getCategoryId());
        }
        if(isSalary) {
            Categories category = categoryRepository.findByNameIgnoreCase("salary");
            transaction.setCategoryId(category.getCategoryId());
        }
        else {
            transaction.setCategoryId(8L);
        }
    }

    public BankAccount addTransactionToBankAccount(Transaction transaction) {
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getBankAccountId())
                .orElseThrow(() -> new InvalidPropertyState("The bankaccount or transaction is not found"));
        if (transaction.getIncomingAmount() != null) {
            bankAccount.setBalance(bankAccount.getBalance().add(transaction.getIncomingAmount()));
        } else if (transaction.getOutgoingAmount() != null) {
            bankAccount.setBalance(bankAccount.getBalance().subtract(transaction.getOutgoingAmount()));
        }
        return bankAccountRepository.save(bankAccount);
    }

    public List<Transaction> findAllTransactionByBankAccountId(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new InvalidPropertyState("The bankaccount or transaction is not found"));;
        return transactionRepository.findAllTransactionByBankAccountId(bankAccount.getBankAccountId());
    }
}

