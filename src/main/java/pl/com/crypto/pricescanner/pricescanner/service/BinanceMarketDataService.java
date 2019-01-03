package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.RequiredArgsConstructor;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Component;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceMarketDataAdapter;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BinanceMarketDataService implements MarketDataService {

    private final BinanceMarketDataAdapter binanceMarketDataAdapter;

    @Override
    public List<Bar> getHistory(CurrencyPair currencyPair, CandleDuration duration) throws IOException {
        return binanceMarketDataAdapter.getHistory(currencyPair, duration);
    }
}
