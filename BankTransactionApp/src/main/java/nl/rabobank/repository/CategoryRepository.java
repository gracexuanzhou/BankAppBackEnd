package nl.rabobank.repository;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

public interface CategoryRepository extends JpaRepository<Categories,Long> {
    Categories findByNameIgnoreCase(String name);

    Categories findCategoriesByCategoryId(Long categoryId);
}
