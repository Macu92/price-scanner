package pl.com.crypto.pricescanner.pricescanner.service;

import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.stereotype.Component;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.Decimal;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.model.Market;

import java.time.Instant;
import java.util.List;

@Component
public class BarService {

    public void updateActualBar(Ticker ticker, Market market) {
        Bar actualBar = market.getActualBar();
        List<Bar> bars = market.getBars();

        if (actualBar == null) {
            actualBar = bars.get(bars.size() - 1);
            market.setActualBar(actualBar);
        } else if (actualBar.getEndTime().toInstant().isBefore(Instant.now()) && actualBar != null) {
            actualBar = createNewActualBar(
                    Decimal.valueOf(ticker.getLast().doubleValue()),
                    market.getDuration(),
                    actualBar);
            market.setActualBar(actualBar);
            bars.add(actualBar);
        }
        actualBar.addTrade(ticker.getVolume().doubleValue(), ticker.getLast().doubleValue());
    }

    private Bar createNewActualBar(Decimal lastTradeValue, CandleDuration candleDuration, Bar actualBar) {
        return new BaseBar(candleDuration.getDuration(),
                actualBar.getEndTime().plus(candleDuration.getDuration()),
                lastTradeValue,
                lastTradeValue,
                lastTradeValue,
                lastTradeValue,
                Decimal.ZERO);
    }
}
