package nl.rabobank.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity(name = "category")
@Table (name = "categories")

public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long categoryId;

    @Column(name = "name")
    private String name;




    public Categories(){}

    public Categories(Long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }


    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
