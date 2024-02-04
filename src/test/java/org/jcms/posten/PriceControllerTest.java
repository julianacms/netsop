package org.jcms.posten;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class PriceControllerTest {
    @Mock
    private PriceService priceService;

    @InjectMocks
    private PriceController controller;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getPrice_test() {
        when(priceService.getPrice(any(), any())).thenReturn(100);

        Integer price = controller.getPrice(1, 10).getBody();

        assertThat(price).isEqualTo(100);
    }

    @Test
    public void getPrices_test() {
        when(priceService.getPopularPrices()).thenReturn(prices());

        PriceOptions priceOptions = controller.getPriceOptions().getBody();

        assertThat(priceOptions).isEqualTo(prices());
    }


    private PriceOptions prices() {
        List<Price> prices = new ArrayList<>();

        prices.add(new Price(1, 2, 3));
        prices.add(new Price(4, 5, 6));
        prices.add(new Price(7, 8, 9));
        prices.add(new Price(10, 11, 12));

        return new PriceOptions(10, 11, prices);
    }
}