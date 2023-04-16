package nl.rabobank.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity(name = "transaction")
@Table(name = "transactions")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "incomingAmount")
    private BigDecimal incomingAmount;

    @Column(name = "outgoingAmount")
    private BigDecimal outgoingAmount;

    @Column(name = "date")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_account_id")
    @JsonIgnore
    private BankAccount bankAccount;

    @OneToOne(fetch = FetchType.LAZY, cascade =  CascadeType.ALL)
    @JoinColumn(name = "category_id")
    @JsonIgnore
    private Categories categories;

    public Transaction(){
    }

    public Transaction(Long id, BigDecimal incomingAmount, BigDecimal outgoingAmount, LocalDateTime date, BankAccount bankAccount,
    Categories categories){
        this.id = id;
        this.incomingAmount = incomingAmount;
        this.outgoingAmount = outgoingAmount;
        this.date = date;
        this.bankAccount = bankAccount;
        this.categories = categories;
    }

    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getIncomingAmount() {
        return incomingAmount;
    }

    public void setIncomingAmount(BigDecimal incomingAmount) {
        this.incomingAmount = incomingAmount;
    }

    public BigDecimal getOutgoingAmount() {
        return outgoingAmount;
    }

    public void setOutgoingAmount(BigDecimal outgoingAmount) {
        this.outgoingAmount = outgoingAmount;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

}
