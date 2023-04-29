package nl.rabobank.controller;

import nl.rabobank.model.FootPrintCo2;
import nl.rabobank.model.Transaction;
import nl.rabobank.repository.FootPrintCo2Repository;
import nl.rabobank.service.FootPrintCo2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/footprintco2")
public class FootPrintCo2Controller {

   private final FootPrintCo2Repository footPrintCo2Repository;

   @Autowired
   public FootPrintCo2Controller(FootPrintCo2Repository footPrintCo2Repository) {
      this.footPrintCo2Repository = footPrintCo2Repository;
   }

   @GetMapping
   public List<FootPrintCo2> getAllFootPrintCo2() {
      return footPrintCo2Repository.findAll();
   }
   @GetMapping("/{customerId}")
   public List<FootPrintCo2> getFootPrintCo2ByCustomerId(@PathVariable Long customerId) {
      List<FootPrintCo2> footPrintCo2List = footPrintCo2Repository.findFootPrintCo2ByCustomerId(customerId);

      // Sort the list in descending order based on footprintco2 value
      footPrintCo2List.sort(Comparator.comparing(FootPrintCo2::getFootprintco2).reversed());

      return footPrintCo2List;
   }
}
