package pl.com.crypto.pricescanner.pricescanner.service;

import org.knowm.xchange.dto.marketdata.Ticker;
import org.springframework.stereotype.Component;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.model.Market;

import java.time.Instant;

@Component
public class TimeSeriesService {

    public void updateActualBar(Ticker ticker, Market market) {
        Bar actualBar = market.getActualBar();
        TimeSeries ts = market.getTimeSeries();

        if (actualBar == null) {
            actualBar = ts.getBar(ts.getEndIndex());
            market.setActualBar(actualBar);
        } else if (actualBar.getEndTime().toInstant().isBefore(Instant.now()) && actualBar != null) {
            actualBar = createNewActualBar(
                    PrecisionNum.valueOf(ticker.getLast().longValue()),
                    market.getDuration(),
                    actualBar);
            market.setActualBar(actualBar);
            ts.addBar(actualBar);
        }

        ts.addTrade(
                PrecisionNum.valueOf(ticker.getVolume()),
                PrecisionNum.valueOf(ticker.getLast()));
    }

    private Bar createNewActualBar(Num lastTradeValue, CandleDuration candleDuration, Bar actualBar) {
        return new BaseBar(candleDuration.getDuration(),
                actualBar.getEndTime().plus(candleDuration.getDuration()),
                lastTradeValue,
                lastTradeValue,
                lastTradeValue,
                lastTradeValue,
                PrecisionNum.valueOf(Long.valueOf(0)),
                PrecisionNum.valueOf(Long.valueOf(0)));
    }
}
