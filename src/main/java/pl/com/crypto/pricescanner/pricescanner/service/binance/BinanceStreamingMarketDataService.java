package pl.com.crypto.pricescanner.pricescanner.service.binance;

import io.reactivex.Observer;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import pl.com.crypto.pricescanner.pricescanner.adapter.binance.BinanceStreamingMarketDataAdapter;
import pl.com.crypto.pricescanner.pricescanner.service.StreamingMarketDataService;

import java.util.LinkedList;
import java.util.List;

@Service
@Scope("prototype")
@RequiredArgsConstructor
@Slf4j
public class BinanceStreamingMarketDataService implements StreamingMarketDataService {

    private final BinanceStreamingMarketDataAdapter binanceStreamingMarketDataAdapter;

    private List<Observer> currencyPairObservers = new LinkedList<>();

    @Override
    public void addCurrencyPairObserver(Observer observer) {
        currencyPairObservers.add(observer);
    }

    @Override
    public void observeCurrencyPair(CurrencyPair currencyPair) {
        binanceStreamingMarketDataAdapter.getTicker(currencyPair).subscribe(ticker -> {
            currencyPairObservers.forEach(observer -> observer.onNext(ticker));
        }, throwable -> log.error("Error in subscribing trades.", throwable));
    }

    @Override
    public void disconnect() {
        binanceStreamingMarketDataAdapter.disconnect();
    }

}
