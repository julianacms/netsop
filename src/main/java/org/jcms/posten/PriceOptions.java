package org.jcms.posten;

import java.util.List;

public record PriceOptions(Integer numberOfWeights,
                           Integer numberOfQuantites,
                           List<Price> prices) {
}
