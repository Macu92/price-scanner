package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import pl.com.crypto.pricescanner.pricescanner.error.CurrencyPairNotStreamed;
import pl.com.crypto.pricescanner.pricescanner.model.MarketObserver;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class CurrencyPairService {

    private final ApplicationContext applicationContext;

    private Map<CurrencyPair, StreamingMarketDataService> pairStreamingMarketDataAdapterMap;

    @PostConstruct
    private void init() {
        pairStreamingMarketDataAdapterMap = new HashMap<>();
    }

    public void observeCurrencyPair(MarketObserver marketObserver) throws CurrencyPairNotStreamed {
        StreamingMarketDataService streamingMarketDataService =
                pairStreamingMarketDataAdapterMap.get(marketObserver.getMarket().getCurrencyPair());

        if (streamingMarketDataService == null) {
            throw new CurrencyPairNotStreamed(
                    "Cannot observe " + marketObserver.getMarket().getCurrencyPair().toString() +
                            ". Stream this pair first.");
        }
        streamingMarketDataService.addCurrencyPairObserver(marketObserver);
    }

    public void streamTicker(CurrencyPair currencyPair) {
        StreamingMarketDataService streamingMarketDataService =
                (BinanceStreamingMarketDataService) applicationContext.getBean("binanceStreamingMarketDataService");

        streamingMarketDataService.observeCurrencyPair(currencyPair);
        pairStreamingMarketDataAdapterMap.put(currencyPair, streamingMarketDataService);
    }

    public void disconnect(CurrencyPair currencyPair) {
        pairStreamingMarketDataAdapterMap.get(currencyPair).disconnect();
    }
}
