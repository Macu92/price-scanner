package pl.com.crypto.pricescanner.pricescanner.model;

import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;

import java.util.List;

@Data
@Builder
@Slf4j
public class Market {

    private List<Indicator> indicators;
    private List<Bar> bars;
    private CurrencyPair currencyPair;
    private CandleDuration duration;

}
