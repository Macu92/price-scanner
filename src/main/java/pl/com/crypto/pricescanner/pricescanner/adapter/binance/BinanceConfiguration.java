package pl.com.crypto.pricescanner.pricescanner.adapter.binance;

import lombok.AllArgsConstructor;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class BinanceConfiguration {

    private final BinanceStreamingExchangeAdapter streamingExchangeAdapter;

    @Bean
    public BinanceMarketDataService getBinanceMarketDataService() {
        return (BinanceMarketDataService) streamingExchangeAdapter.getMarketDataService();
    }

}
