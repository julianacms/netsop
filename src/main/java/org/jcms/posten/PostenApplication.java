package org.jcms.posten;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class PostenApplication {
    @Autowired
    PriceRepository priceRepository;

    public static void main(String[] args) {
        SpringApplication.run(PostenApplication.class, args);
    }

    public PostenApplication(PriceRepository priceRepository) { //workaround to initiate the database
        this.priceRepository = priceRepository;
        BasePrice basePrice = new BasePrice();
        basePrice.setId(1);
        basePrice.setValue(50);
        priceRepository.save(basePrice);
    }
}
