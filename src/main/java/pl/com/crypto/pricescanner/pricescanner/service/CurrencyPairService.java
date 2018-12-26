package pl.com.crypto.pricescanner.pricescanner.service;

import io.reactivex.Observable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceMarketDataAdapter;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceStreamingMarketDataAdapter;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyPairService {

    private final BinanceMarketDataAdapter binanceMarketDataAdapter;
    private final ApplicationContext applicationContext;

    private Map<CurrencyPair, BinanceStreamingMarketDataAdapter> pairBinanceStreamingMarketDataAdapterMap;

    @PostConstruct
    private void init() {
        pairBinanceStreamingMarketDataAdapterMap = new HashMap<>();
    }

    public List<Bar> getHistory(CurrencyPair currencyPair, Duration duration) throws IOException {
        return binanceMarketDataAdapter.getHistory(currencyPair, duration);
    }

    public Observable<Ticker> streamTicker(CurrencyPair currencyPair) {
        pairBinanceStreamingMarketDataAdapterMap.put(currencyPair,
                (BinanceStreamingMarketDataAdapter) applicationContext.getBean("binanceStreamingMarketDataAdapter"));
        return pairBinanceStreamingMarketDataAdapterMap.get(currencyPair).getTicker(currencyPair);
    }

    public void disconnect(CurrencyPair currencyPair) {
        pairBinanceStreamingMarketDataAdapterMap.get(currencyPair).disconnect();
    }
}
