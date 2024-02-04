package org.jcms.posten;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class PriceServiceTest {
    @Mock
    PriceRepository priceRepository;

    @InjectMocks
    PriceService priceService;

    @BeforeEach
    public void setup() {
        initMocks(this);
    }

    @Test
    public void test_getPrice() {
        when(priceRepository.findById(anyLong())).thenReturn(Optional.of(new BasePrice(1, 30)));

        Integer obtainedPrice = priceService.getPrice(2, 2);

        assertEquals((int) 80, (int) obtainedPrice);
    }

    @Test
    public void test_getPrice_throws_exception() {
        when(priceRepository.findById(anyLong())).thenThrow(InvalidWeightException.class);

        assertThrows(InvalidWeightException.class, ()-> priceService.getPrice(2, 36));
    }

    @Test
    public void test_getPopularPrice() {
        when(priceRepository.findById(anyLong())).thenReturn(Optional.of(new BasePrice(1, 30)));

        PriceOptions obtainedPrices = priceService.getPopularPrices();

        assertEquals((int) 5, (int) obtainedPrices.numberOfQuantites());
        assertEquals((int) 10, (int) obtainedPrices.numberOfWeights());
    }
}
