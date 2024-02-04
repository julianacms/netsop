package org.jcms.posten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class PriceController {
    @Autowired
    PriceService priceService;

    @GetMapping(value = "/prices")
    public ResponseEntity<PriceOptions> getPriceOptions() {
        return ResponseEntity.ok(priceService.getPopularPrices());
    }

    @GetMapping(value = "/price")
    public ResponseEntity<Integer> getPrice(@RequestParam Integer quantity,
                                               @RequestParam Integer weight) {
        try {
            return ResponseEntity.ok(priceService.getPrice(quantity, weight));
        } catch (InvalidWeightException e) {
            return ResponseEntity.badRequest().build();
        }

    }
}
