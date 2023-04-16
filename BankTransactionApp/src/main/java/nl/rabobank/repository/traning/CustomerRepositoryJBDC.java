/*
package nl.rabobank.repository.traning;

import nl.rabobank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;



@Repository
@Primary
public class CustomerRepositoryJBDC implements CustomerRepository{

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Override
    public Customer save(Customer customer) {
        final String updatesql = "INSERT INTO public.customers(\"firstName\",lastname,email,bank_account_id) VALUES(?,?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(updatesql);
                ps.setString(1, customer.getFirstName());
                ps.setString(2,customer.getLastName());
                ps.setString(3, customer.getEmail());
                ps.setLong(4,customer.getBankAccount().getId());
                return ps;
            }
        },keyHolder);
        customer.setId((long) keyHolder.getKey());
        return customer;
    }

    @Override
    public Optional<Customer> findById(Long customerId) {
        return Optional.of(jdbcTemplate.queryForObject(
                "SELECT * FROM customers WHERE id = ?",new Object[] { customerId},
                new CustomerMapper()));
    }

    @Override
    public void deleteById(Long customerId) {
        jdbcTemplate.update("DELETE FROM customers WHERE ID = ?", customerId);

    }

    @Override
    public List<Customer> findAll() {
        return jdbcTemplate.queryForList(
                "SELECT  * FROM customers", Customer.class);
    }
}

class CustomerMapper implements RowMapper<Customer> {
    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        Customer customer = new Customer();

        customer.setId(rs.getLong("id"));
        customer.setEmail(rs.getString("email"));
        customer.setFirstName(rs.getString("firstName"));
        customer.setLastName(rs.getString("lastName"));

        //should do an inner join table with bankaccounts
        //no easy way to get this
        customer.setBankAccount(rs.getLong("bank_account_id"));
        return customer;
    }
}
*/