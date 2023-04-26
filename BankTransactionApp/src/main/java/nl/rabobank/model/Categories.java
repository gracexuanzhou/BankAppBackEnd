package nl.rabobank.model;

import javax.persistence.*;

@Entity(name = "category")
@Table (name = "categories")

public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long categoryId;

    @Column(name = "name")
    private String name;

   // private double co2FootPrint;



    public Categories(){}

    public Categories(Long categoryId, String name){
        this.categoryId = categoryId;
        this.name = name;
    }

    /*public Categories(Long categoryId, String name, double co2FootPrint){
        this.categoryId = categoryId;
        this.name = name;
        this.co2FootPrint = co2FootPrint;
    }*/

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
