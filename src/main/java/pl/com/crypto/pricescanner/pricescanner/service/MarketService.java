package pl.com.crypto.pricescanner.pricescanner.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Service;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.model.Market;

import java.io.IOException;
import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MarketService {

    private final CurrencyPairService currencyPairService;
    private List<Market> markets;

    // liste mozna zamienic pozniej na mape z typami par i na kazdy tick bylyby notyfikowane tylko te rynki ktore
    // tycza sie tej pary np linkedhashmapa z key btc_usdt value 2x market z btcusdt gdy wchdzi ticker to pobiera
    // tylko te dwa markety i je notyfikuje, cos takiego zamiast przetrzymywania observable
    public void addMarket(CurrencyPair currencyPair, Duration duration) {
        if (markets == null) {
            markets = new LinkedList<>();
        }

        try {
            Market market = createMarket(currencyPair, duration);
            market.subscribeObservable(currencyPairService.streamTicker(market.getCurrencyPair()));
            markets.add(market);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void disconnectAllMarkets() {
        markets.forEach(market -> currencyPairService.disconnect(market.getCurrencyPair()));
    }

    private Market createMarket(CurrencyPair currencyPair, Duration duration) throws IOException {
        return Market.builder()
                .duration(CandleDuration.m1.getDuration())
                .currencyPair(CurrencyPair.BTC_USDT)
                .bars(currencyPairService.getHistory(currencyPair, duration))
                .build();
    }
}
