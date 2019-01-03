package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.error.CurrencyPairNotStreamed;
import pl.com.crypto.pricescanner.pricescanner.model.Market;
import pl.com.crypto.pricescanner.pricescanner.model.MarketObserver;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketService {

    private final CurrencyPairService currencyPairService;
    private final MarketDataService marketDataService;
    private List<Market> markets;

    public void createMarket(CurrencyPair currencyPair, CandleDuration duration) {
        if (markets == null) {
            markets = new LinkedList<>();
        }

        try {
            Market market = buildMarket(currencyPair, duration);
            markets.add(market);
            currencyPairService.observeCurrencyPair(createMarketObserver(market));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CurrencyPairNotStreamed currencyPairNotStreamed) {
            log.error(currencyPairNotStreamed.getMessage(), currencyPairNotStreamed);
        }
    }

    private MarketObserver createMarketObserver(Market market) {
        return new MarketObserver(market);
    }

    public void disconnectAllMarkets() {
        markets.forEach(market -> currencyPairService.disconnect(market.getCurrencyPair()));
    }

    private Market buildMarket(CurrencyPair currencyPair, CandleDuration duration) throws IOException {
        return Market.builder()
                .duration(duration)
                .currencyPair(currencyPair)
                .bars(marketDataService.getHistory(currencyPair, duration))
                .build();
    }
}
