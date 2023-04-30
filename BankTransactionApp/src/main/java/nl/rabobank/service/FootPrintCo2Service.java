package nl.rabobank.service;

import nl.rabobank.model.FootPrintCo2;

import java.util.List;

public interface FootPrintCo2Service {
    List<FootPrintCo2> findFootPrintCo2ByCustomerId(Long customerId);

    List<FootPrintCo2> findAll();
}
