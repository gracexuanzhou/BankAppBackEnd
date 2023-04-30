package nl.rabobank.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "footprintco2")
@Table(name = "footprintCo2")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FootPrintCo2 {


    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "category_id")
    private Long categoryId;

    @Column(name = "footprint_co2")
    private BigDecimal footprintco2;

    @Column(name = "category_name")
    private String categoryName;



    public FootPrintCo2(Long customerId, Long categoryId, BigDecimal footprintco2, String categoryName) {
        this.customerId = customerId;
        this.categoryId = categoryId;
        this.footprintco2 = footprintco2;
        this.categoryName = categoryName;
    }

    public FootPrintCo2(){}


    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public BigDecimal getFootprintco2() {
        return footprintco2;
    }

    public void setFootprintco2(BigDecimal footprintco2) {
        this.footprintco2 = footprintco2;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
