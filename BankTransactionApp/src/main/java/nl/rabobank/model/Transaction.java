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

    @Column(name = "bank_account_id")
    private Long bankAccountId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "description")
    private String description;

    public Transaction(){
    }

    public Transaction(Long id, BigDecimal incomingAmount, BigDecimal outgoingAmount, LocalDateTime date,
                       Long bankAccountId, Long categoryId, String description){
        this.id = id;
        this.incomingAmount = incomingAmount;
        this.outgoingAmount = outgoingAmount;
        this.date = date;
        this.bankAccountId = bankAccountId;
        this.categoryId = categoryId;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Long getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(Long bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
