package nl.rabobank.service;

import nl.rabobank.model.FootPrintCo2;
import nl.rabobank.repository.FootPrintCo2Repository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Comparator;
import java.util.List;

public class FootPrintCo2ServiceImpl implements FootPrintCo2Service{

    @Autowired
    FootPrintCo2Repository footPrintCo2Repository;

    @Override
    public List<FootPrintCo2> findFootPrintCo2ByCustomerId(Long customerId) {
        List<FootPrintCo2> footPrintCo2List = footPrintCo2Repository.findFootPrintCo2ByCustomerId(customerId);
        // Sort the list in descending order based on footprintco2 value
        footPrintCo2List.sort(Comparator.comparing(FootPrintCo2::getFootprintco2).reversed());
        return footPrintCo2List;
    }

    @Override
    public  List<FootPrintCo2> findAll(){
        return footPrintCo2Repository.findAll();
    }
}
