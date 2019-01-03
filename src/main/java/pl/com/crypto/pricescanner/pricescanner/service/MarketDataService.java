package pl.com.crypto.pricescanner.pricescanner.service;

import org.knowm.xchange.currency.CurrencyPair;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;

import java.io.IOException;
import java.util.List;

public interface MarketDataService {
    List<Bar> getHistory(CurrencyPair currencyPair, CandleDuration duration) throws IOException;
}
