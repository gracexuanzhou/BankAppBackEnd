/*package nl.rabobank.repository.traning;

import nl.rabobank.model.Customer;
import nl.rabobank.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class CustomerRepositoryImpl implements CustomerRepository {

    private LinkedHashMap<Long, Customer> customersById = new LinkedHashMap<>();

    private Long getNextId(){
        Iterator<Map.Entry<Long, Customer>> iterator = customersById.entrySet().iterator();
        if(!iterator.hasNext()){
            return 1L;
        }

        Map.Entry<Long,Customer> lastEntry = null;
        while (iterator.hasNext()){
            lastEntry = iterator.next();
        }

        return lastEntry.getKey()+1;

    }
    @Override
    public Customer save(Customer customer) {
        Customer customerObj = new Customer();
        customerObj.setCustomerId(customer.getCustomerId()== null? getNextId(): customer.getCustomerId());
        customerObj.setEmail(customer.getEmail());
        customerObj.setLastName(customer.getLastName());
        customerObj.setFirstName(customer.getFirstName());
        customerObj.setBankAccount(customer.getBankAccount());
        customersById.put(customerObj.getCustomerId(), customerObj);
        return customerObj;
    }


    @Override
    public Optional<Customer> findById(Long customerId) {
        return Optional.ofNullable(customersById.get(customerId));
    }

    @Override
    public void deleteById(Long customerId) {
        customersById.remove(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return new ArrayList(customersById.values());
    }
}*/
