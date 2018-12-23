package pl.com.crypto.pricescanner.pricescanner.adapter.binance;

import io.reactivex.Observable;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class BinanceStreamingMarketDataAdapter {

    private final BinanceStreamingExchangeAdapter binanceStreamingExchangeAdapter;

    public Observable<Ticker> getTicker(CurrencyPair currencyPair){
        if(!binanceStreamingExchangeAdapter.isAlive()) {
            binanceStreamingExchangeAdapter.connectTickers(currencyPair);
        }
        return binanceStreamingExchangeAdapter.getStreamingMarketDataService().getTicker(currencyPair);
    }

    public void disconnect() {
        binanceStreamingExchangeAdapter.disconnect();
    }
}
