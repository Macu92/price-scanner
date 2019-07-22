package pl.com.crypto.pricescanner.pricescanner.model;

import lombok.Data;
import org.ta4j.core.Indicator;
import org.ta4j.core.Strategy;

import java.util.HashMap;
import java.util.Map;

@Data
public class TradingStrategy {

    private Strategy strategy;
    private Map<String, Indicator> indicators;
    private Market market;

    public void addIndicator(Indicator indicator) {
        if (indicators == null) {
            indicators = new HashMap<>();
        }
        indicators.put(indicator.getClass().getSimpleName(), indicator);
    }
}
