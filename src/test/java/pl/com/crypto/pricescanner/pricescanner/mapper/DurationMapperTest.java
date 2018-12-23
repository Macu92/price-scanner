package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.junit.Test;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import pl.com.crypto.pricescanner.pricescanner.error.UnsupportedCandleIntervalException;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class DurationMapperTest {

    @Test
    public void shouldMapKlineIntervalToDuration() {
        Duration m1 = DurationMapper.map(KlineInterval.m1);
        assertEquals(KlineInterval.m1.getMillis().longValue(), m1.toMillis());

        Duration h1 = DurationMapper.map(KlineInterval.h1);
        assertEquals(KlineInterval.h1.getMillis().longValue(), h1.toMillis());

        Duration d1 = DurationMapper.map(KlineInterval.d1);
        assertEquals(KlineInterval.d1.getMillis().longValue(), d1.toMillis());
    }

    @Test
    public void shouldMapDurationToKlineInterval() {
        KlineInterval m1 = DurationMapper.map(Duration.ofMinutes(1));
        assertEquals(Duration.ofMinutes(1).toMillis(),m1.getMillis().longValue());

        KlineInterval h1 = DurationMapper.map(Duration.ofHours(1));
        assertEquals(Duration.ofHours(1).toMillis(),h1.getMillis().longValue());

        KlineInterval d1 = DurationMapper.map(Duration.ofDays(1));
        assertEquals(Duration.ofDays(1).toMillis(),d1.getMillis().longValue());
    }

    @Test(expected = UnsupportedCandleIntervalException.class)
    public void shouldThorwUnsupportedCandleIntervalException(){
        DurationMapper.map(Duration.ofHours(3));
    }
}