package nl.rabobank.service;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import nl.rabobank.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Customer createNew(Customer customer);

    void update(Customer customer);

    Optional<Customer> findById(Long customerId);

    void deleteById(Long customerId);

    List<Customer> findAll();

    CustomerRepository getCustomerRepository();

    void setCustomerRepository(CustomerRepository customerRepository);

    //Customer addBankAccountToCustomer(Long customerID, BankAccount bankAccount);
}
