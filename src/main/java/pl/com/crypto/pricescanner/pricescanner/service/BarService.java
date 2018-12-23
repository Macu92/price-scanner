package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceMarketDataAdapter;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceStreamingMarketDataAdapter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class BarService {

    private final BinanceMarketDataAdapter binanceMarketDataAdapter;
    private final BinanceStreamingMarketDataAdapter binanceStreamingMarketDataAdapter;

    public List<Bar> getHistory(CurrencyPair currencyPair, Duration duration) throws IOException {
        return binanceMarketDataAdapter.getHistory(currencyPair, duration);
    }

    public void streamTicker(CurrencyPair currencyPair) {
        binanceStreamingMarketDataAdapter.getTicker(currencyPair).subscribe(ticker -> {
            log.info("Incoming trade: {}", ticker);
        }, throwable -> {
            log.error("Error in subscribing trades.", throwable);
        });
    }

    public void disconnect() {
        binanceStreamingMarketDataAdapter.disconnect();
    }
}
