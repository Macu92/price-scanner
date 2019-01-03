package pl.com.crypto.pricescanner.pricescanner.service;

import io.reactivex.Observer;
import org.knowm.xchange.currency.CurrencyPair;

public interface StreamingMarketDataService {
    void addCurrencyPairObserver(Observer observer);

    void observeCurrencyPair(CurrencyPair currencyPair);

    void disconnect();
}
