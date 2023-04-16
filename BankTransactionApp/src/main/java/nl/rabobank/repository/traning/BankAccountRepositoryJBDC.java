
/*
package nl.rabobank.repository;

import nl.rabobank.model.BankAccount;
import nl.rabobank.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class BankAccountRepositoryJBDC implements BankAccountRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public BankAccount save(BankAccount bankAccount) {
        final String updateBankAccount = "INSERT INTO public.bankaccounts(iban) VALUES(?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(updateBankAccount);
                ps.setString(1,bankAccount.getIBAN());
                return ps;
            }
        },keyHolder);
        bankAccount.setId((Long) keyHolder.getKey());
        return bankAccount;
    }

    @Override
    public Optional<BankAccount> findById(Long bankAccountId) {
        return Optional.of(jdbcTemplate.queryForObject(
                "SELECT * FROM bankaccounts WHERE id = ?",new Object[] { bankAccountId},
                new BankAccountMapper()));

    }

    @Override
    public void deleteById(Long bankAccountId) {
        jdbcTemplate.update("DELETE FROM bankaccounts WHERE id = ?", bankAccountId);
    }

    @Override
    public List<BankAccount> findAll() {
        return jdbcTemplate.queryForList("SELECT * FROM bankaccounts", BankAccount.class);
    }

}

class BankAccountMapper implements RowMapper<BankAccount> {
    @Override
    public BankAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setId(rs.getLong("ID"));
        bankAccount.setIBAN(rs.getString("Iban"));
        return bankAccount;
    }
}

 */