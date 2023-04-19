package nl.rabobank.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "bankaccount")
@Table(name = "bankaccounts")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "iban")
    private String iban;

    @Column(name = "balance")
    private BigDecimal balance;

    @Column(name = "customer_id")
    private Long customerId;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }



    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private List<Transaction> transactionList = new ArrayList<>();

    public BankAccount(){}

    public BankAccount(Long id, String iban, BigDecimal balance, Long customerId){
        this.id = id;
        this.iban = iban;
        this.balance = balance;
        this.customerId = customerId;
        //this.customerId = customerId;
    }


    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    /*public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }*/

    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }
}
