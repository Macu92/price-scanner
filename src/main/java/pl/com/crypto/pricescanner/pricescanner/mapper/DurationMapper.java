package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import pl.com.crypto.pricescanner.pricescanner.error.UnsupportedCandleIntervalException;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

public class DurationMapper {

    public static Duration map(KlineInterval klineInterval) {
        return Duration.ofMillis(klineInterval.getMillis());
    }

    public static KlineInterval map(Duration duration) {
        KlineInterval klineInterval = Arrays.stream(
                KlineInterval.values())
                .filter(interval -> interval.getMillis().equals(duration.toMillis()))
                .findFirst()
                .orElseThrow(() -> new UnsupportedCandleIntervalException(duration.toString()));
        return klineInterval;
    }
}
