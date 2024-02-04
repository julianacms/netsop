package org.jcms.posten;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;


@Service
@Slf4j
public class PriceService {
    public static Integer BASE_FEE = 10;
    public static Integer PRICE_PER_KG = 5;

    @Autowired
    private PriceRepository priceRepository;

    List<Integer> weightOptions = IntStream.rangeClosed(1, 10).boxed().toList();
    List<Integer> quantityOptions = IntStream.rangeClosed(1, 5).boxed().toList();

    public Integer getPrice(Integer quantity, Integer weight) {
        if(weight < 0 || weight > 35) {
            throw new InvalidWeightException();
        }
        Integer baseprice = priceRepository.findById(Long.valueOf(1))
                .get().getValue();
        double price = (baseprice + Math.ceil(weight-1) * PRICE_PER_KG) * quantity + BASE_FEE;
        return (int) price;
    }

    public PriceOptions getPopularPrices() {
        List<Price> prices = new ArrayList<>();
        weightOptions.forEach(w-> quantityOptions.forEach(
                        q-> prices.add(new Price(w, q, getPrice(q, w)))));
        return new PriceOptions(weightOptions.size(), quantityOptions.size(), prices);
    }
}
