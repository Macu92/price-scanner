package pl.com.crypto.pricescanner.pricescanner.model;

import io.reactivex.Observable;
import lombok.Builder;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.ta4j.core.Bar;
import org.ta4j.core.Indicator;

import java.time.Duration;
import java.util.List;

@Data
@Builder
@Slf4j
public class Market {

    private List<Indicator> indicators;
    private List<Bar> bars;
    private CurrencyPair currencyPair;
    private Duration duration;

    public void subscribeObservable(Observable<Ticker> tickerObservable) {
        tickerObservable.subscribe(ticker -> {
            log.info("Incoming trade: {}", ticker);
        }, throwable -> {
            log.error("Error in subscribing trades.", throwable);
        });
    }
}
