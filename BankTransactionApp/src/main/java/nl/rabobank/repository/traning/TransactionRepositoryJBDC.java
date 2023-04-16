
/*
package nl.rabobank.repository;

import nl.rabobank.model.Customer;
import nl.rabobank.model.Transaction;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class TransactionRepositoryJBDC implements TransactionRepository {

    @Override
    public Transaction save(Transaction transaction) {

        return null;
    }

    @Override
    public Optional<Transaction> findById(Long transactionId) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Long transactionId) {

    }

    @Override
    public List<Transaction> findAll() {
        return null;
    }
}

class TranscationMapper implements RowMapper<Transaction> {
    @Override
    public Transaction mapRow(ResultSet rs, int rowNum) throws SQLException {
        Transaction transaction = new Transaction();

        transaction.setId(rs.getLong("ID"));
        //transaction.s

        return transaction;
    }
}

 */
