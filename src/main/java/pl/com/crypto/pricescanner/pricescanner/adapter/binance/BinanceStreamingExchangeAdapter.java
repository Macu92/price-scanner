package pl.com.crypto.pricescanner.pricescanner.adapter.binance;

import info.bitrich.xchangestream.binance.BinanceStreamingExchange;
import info.bitrich.xchangestream.core.ProductSubscription;
import info.bitrich.xchangestream.core.StreamingExchange;
import info.bitrich.xchangestream.core.StreamingExchangeFactory;
import info.bitrich.xchangestream.core.StreamingMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.service.marketdata.MarketDataService;
import org.springframework.stereotype.Component;

@Component
public class BinanceStreamingExchangeAdapter {

    private final StreamingExchange exchange;

    public BinanceStreamingExchangeAdapter() {
        exchange = StreamingExchangeFactory.INSTANCE.createExchange(BinanceStreamingExchange.class.getName());
    }

    public void connect(ProductSubscription productSubscription){
       exchange.connect(productSubscription).blockingAwait();
    }

    public void connectTickers(CurrencyPair currencyPair){
        connect(ProductSubscription.create().addTicker(currencyPair).build());
    }

    public void disconnect(){
        exchange.disconnect();
    }

    public boolean isAlive(){
        return exchange.isAlive();
    }

    public StreamingMarketDataService getStreamingMarketDataService(){
        return exchange.getStreamingMarketDataService();
    }

    public MarketDataService getMarketDataService(){
        return exchange.getMarketDataService();
    }
}
