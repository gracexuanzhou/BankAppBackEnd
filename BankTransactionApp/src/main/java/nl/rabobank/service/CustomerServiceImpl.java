package nl.rabobank.service;

import nl.rabobank.InvalidPropertyState;
import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Categories;
import nl.rabobank.model.Customer;
import nl.rabobank.model.FootPrintCo2;
import nl.rabobank.repository.BankAccountRepository;
import nl.rabobank.repository.CategoryRepository;
import nl.rabobank.repository.CustomerRepository;
import nl.rabobank.repository.FootPrintCo2Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
   @Autowired
    CustomerRepository customerRepository;
   @Autowired
    BankAccountRepository bankAccountRepository;
    @Autowired
    FootPrintCo2Repository footPrintCo2Repository;
    @Autowired
    CategoryRepository categoryRepository;


   public Customer getCustomerById(Long customerId){
       return customerRepository.findById(customerId)
               .orElseThrow(() -> new InvalidPropertyState("customer id not find"));
   }
    @Override
    public Customer createNew(Customer customer) {
        if (customer.getCustomerId() != null) {
            throw new InvalidPropertyState("id does not have to be set for new customer");
        }
        //setCustomerFootprint(customer.getCustomerId());
        Customer savedCustomer = customerRepository.save(customer);
        setFootPrint(savedCustomer.getCustomerId());
        return savedCustomer;
    }

    @Override
    public void update(Customer customer) {
        if (customer.getCustomerId() == null) {
            throw new InvalidPropertyState("id has to be set");
        }
        customerRepository.save(customer);
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return customerRepository.findById(customerId);
    }

    @Override
    public void deleteById(Long customerId) {
        Customer customer = getCustomerById(customerId);
        customerRepository.deleteById(customerId);
        List<BankAccount> bankAccounts = customer.getBankAccount();
        for(BankAccount bankAccount: bankAccounts){
            bankAccountRepository.deleteById(bankAccount.getBankAccountId());
        }
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public CustomerRepository getCustomerRepository() {
        return customerRepository;
    }

    @Override
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public void setFootPrint(Long customerId){
       BigDecimal co2 = BigDecimal.valueOf(0);

       for(int i = 1; i<=8; i++){
            Long categoryId = (long)i;
           Categories categories = categoryRepository.findCategoriesByCategoryId(categoryId);
           FootPrintCo2 footPrintCo2 = new FootPrintCo2(customerId,categoryId,co2,categories.getName());
            footPrintCo2Repository.save(footPrintCo2);
        }
    }


}
