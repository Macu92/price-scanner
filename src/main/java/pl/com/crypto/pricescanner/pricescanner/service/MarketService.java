package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceMarketDataAdapter;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Service
@Slf4j
public class MarketService {

    private final BinanceMarketDataAdapter binanceMarketDataAdapter;


    public MarketService(BinanceMarketDataAdapter binanceMarketDataAdapter) {
        this.binanceMarketDataAdapter = binanceMarketDataAdapter;
    }

    public List<Bar> getHistory(CurrencyPair currencyPair, Duration duration) throws IOException {
            return binanceMarketDataAdapter.getHistory(currencyPair, duration);
    }
}
