package nl.rabobank.service;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Categories;
import nl.rabobank.model.FootPrintCo2;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.BankAccountRepository;
import nl.rabobank.repository.CategoryRepository;
import nl.rabobank.repository.FootPrintCo2Repository;
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

    @Autowired
    FootPrintCo2Repository footPrintCo2Repository;

    private BigDecimal groceriesCo2 = new BigDecimal(0.05);

    private BigDecimal diningCo2 = new BigDecimal(0.08);

    private BigDecimal transportCo2 = new BigDecimal(0.05);

    private BigDecimal shoppingCo2 = new BigDecimal(0.03);

    private BigDecimal housingCo2 = new BigDecimal(0.06);

    private BigDecimal entertainmentCo2 = new BigDecimal(0.01);

    private BigDecimal othersCo2 = new BigDecimal(0.01);


    @Override
    public Transaction createTransaction(Transaction transaction){
        if(transaction.getId() != null){
            throw new InvalidPropertyState("id does not have to be set for transaction");
        }
        addTransactionToBankAccount(transaction);
        categorizeTransaction(transaction);
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
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getBankAccountId()).
                orElseThrow();

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
            setTransactionCategory(category,transaction,transportCo2);
           // updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());


        }

        else if(isEntertainment){
            Categories category = categoryRepository.findByNameIgnoreCase("entertainment");
            setTransactionCategory(category,transaction,entertainmentCo2);
            //updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
        else if(isDining){
            Categories category = categoryRepository.findByNameIgnoreCase("dining");
            setTransactionCategory(category,transaction,diningCo2);
            //updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
        else if(isGroceries){
            Categories category = categoryRepository.findByNameIgnoreCase("groceries");
            setTransactionCategory(category,transaction,groceriesCo2);
            //updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
        else if(isShopping){
            Categories category = categoryRepository.findByNameIgnoreCase("shopping");
            setTransactionCategory(category,transaction,shoppingCo2);
           // updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
        else if(isHousingExpenses){
            Categories category = categoryRepository.findByNameIgnoreCase("housing Expenses");
            setTransactionCategory(category,transaction,housingCo2);
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
           // updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
        else if(isSalary) {
            Categories category = categoryRepository.findByNameIgnoreCase("salary");
            setTransactionCategory(category,transaction,BigDecimal.valueOf(0));
        }
        else {
            Categories category = categoryRepository.findByNameIgnoreCase("others");
            setTransactionCategory(category,transaction,othersCo2);
            updateFootPrint(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
            //updateCo2Value(bankAccount.getCustomerId(), category.getCategoryId(), transaction.getSigleCo2Amount());
        }
    }

    public BankAccount addTransactionToBankAccount(Transaction transaction) {
        BankAccount bankAccount = bankAccountRepository.findById(transaction.getBankAccountId())
                .orElseThrow(() -> new InvalidPropertyState("The bankaccount or transaction is not found"));
        if (transaction.getIncomingAmount() != null) {
            bankAccount.setBalance(bankAccount.getBalance().add(transaction.getIncomingAmount()));
        }
        if (transaction.getOutgoingAmount() != null) {
            bankAccount.setBalance(bankAccount.getBalance().subtract(transaction.getOutgoingAmount()));
        }
        return bankAccountRepository.save(bankAccount);
    }

    public List<Transaction> findAllTransactionByBankAccountId(Long bankAccountId) {
        BankAccount bankAccount = bankAccountRepository.findById(bankAccountId).orElseThrow(() -> new InvalidPropertyState("The bankaccount or transaction is not found"));;
        return transactionRepository.findAllTransactionByBankAccountId(bankAccount.getBankAccountId());
    }

    public void setTransactionCategory(Categories category, Transaction transaction,BigDecimal hardcodeCo2Value){
        transaction.setCategoryId(category.getCategoryId());
        transaction.setCategory(category.getName());
        if (transaction.getOutgoingAmount()!=null) {
            transaction.setSigleCo2Amount(transaction.getOutgoingAmount().multiply(hardcodeCo2Value));
        }
    }

    public void updateFootPrint(Long customerId, Long categoryId, BigDecimal newCo2Value){
        List<FootPrintCo2> footPrintCo2s = footPrintCo2Repository.findFootPrintCo2ByCustomerId(customerId);

        for(FootPrintCo2 footPrintCo2: footPrintCo2s){
            if(footPrintCo2.getCategoryId() == categoryId){
                footPrintCo2.setFootprintco2(newCo2Value.add(footPrintCo2.getFootprintco2()));
                footPrintCo2Repository.save(footPrintCo2);
            }
        }

    }

}

