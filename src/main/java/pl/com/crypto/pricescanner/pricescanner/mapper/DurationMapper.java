package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.knowm.xchange.binance.dto.marketdata.KlineInterval;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

public class DurationMapper {

    public static Duration map(KlineInterval klineInterval) {
        return Duration.ofMillis(klineInterval.getMillis());
    }

    public static KlineInterval map(Duration duration) {
        Optional<KlineInterval> optionalKlineInterval =
                Arrays.stream(KlineInterval.values()).filter(klineInterval -> klineInterval.getMillis().equals(duration.toMillis())).findFirst();
        return optionalKlineInterval.get();
    }
}
