package pl.com.crypto.pricescanner.pricescanner.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import org.ta4j.core.TimeSeries;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@Slf4j
public class Market {

    private Map<String, Indicator> indicators;
    private TimeSeries timeSeries;
    private CurrencyPair currencyPair;
    private CandleDuration duration;

    private Bar actualBar;

    public void addIndicator(Indicator indicator) {
        if (indicators == null) {
            indicators = new HashMap<>();
        }
        indicators.put(indicator.getClass().getSimpleName(), indicator);
    }

}
