package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceMarketDataAdapter;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceStreamingMarketDataAdapter;
import pl.com.crypto.pricescanner.pricescanner.model.Market;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
public class MarketService {

    private List<Market> markets;

    public void addMarket(Market market){
        if(markets ==null){
            markets = new LinkedList<>();
        }
        markets.add(market);
    }
}
