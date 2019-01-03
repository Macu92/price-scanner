package pl.com.crypto.pricescanner.pricescanner.adapter.binance;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.binance.service.BinanceMarketDataService;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.stereotype.Component;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.mapper.BarMapper;
import pl.com.crypto.pricescanner.pricescanner.mapper.DurationMapper;

import java.io.IOException;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
@Slf4j
public class BinanceMarketDataAdapter {

    private final BinanceMarketDataService binanceMarketDataService;

    public List<Bar> getHistory(CurrencyPair currencyPair, CandleDuration duration) throws IOException {
        return binanceMarketDataService.klines(currencyPair, DurationMapper.map(duration)).stream()
                .map(BarMapper::map).collect(Collectors.toList());
    }

}
