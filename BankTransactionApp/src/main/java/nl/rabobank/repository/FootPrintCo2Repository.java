package nl.rabobank.repository;

import nl.rabobank.model.FootPrintCo2;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FootPrintCo2Repository extends JpaRepository<FootPrintCo2,Long> {

    List<FootPrintCo2> findFootPrintCo2ByCustomerId(Long customerId);

    List<FootPrintCo2> findAll();

}
