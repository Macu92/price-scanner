package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.marketdata.Ticker;
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
    private final BarService barService;
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
        return new MarketObserver(market) {
            @Override
            public void onNext(Object ticker) {
                log.info(String.format("Duration %s got Ticker: %s", market.getDuration(), ticker));
                barService.updateActualBar((Ticker) ticker, market);
                log.info("LAST TEN TICKERS");
                for (int i = market.getBars().size(); market.getBars().size() - 10 < i; i--) {
                    log.info(market.getBars().get(i - 1).getBeginTime() + " " + market.getBars().get(i - 1).toString());
                }
            }
        };
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
