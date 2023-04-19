package nl.rabobank.repository;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
    //Customer save(Customer customer);

    Optional<Customer> findById(Long customerId);

    void deleteById(Long customerId);

    List<Customer> findAll();

}