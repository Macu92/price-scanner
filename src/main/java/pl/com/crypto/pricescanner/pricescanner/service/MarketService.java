package pl.com.crypto.pricescanner.pricescanner.service;

import org.springframework.stereotype.Service;
import pl.com.crypto.pricescanner.pricescanner.adapters.binance.BinanceMarketDataAdapter;

@Service
public class MarketService {

    private final BinanceMarketDataAdapter binanceMarketDataAdapter;


    public MarketService(BinanceMarketDataAdapter binanceMarketDataAdapter) {
        this.binanceMarketDataAdapter = binanceMarketDataAdapter;
    }

}
