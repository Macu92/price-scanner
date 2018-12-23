package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.junit.Before;
import org.junit.Test;
import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.currency.CurrencyPair;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.ta4j.core.Bar;

import java.math.BigDecimal;
import java.time.temporal.ChronoField;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BarMapperTest {

    private BinanceKline binanceKline;

    @Before
    public void setUp() {
        Object[] objects = new Object[]{Long.valueOf(1545125460000L), "3453.63000000", "3459.84000000", "3451.97000000",
                "3458.10000000", "59.86919500", Long.valueOf(1545125519999L), "206898.97259847", Integer.valueOf(261),
                "36.67473800", "126747.13845329", "0"};
        binanceKline = new BinanceKline(CurrencyPair.BTC_USDT, KlineInterval.m1, objects);
    }

    @Test
    public void shouldMapBinanceKlineToBar() {
        Bar bar = BarMapper.map(binanceKline);
        assertEquals(binanceKline.getHighPrice().longValue(), bar.getMaxPrice().longValue());
        assertEquals(binanceKline.getLowPrice().longValue(), bar.getMinPrice().longValue());
        assertEquals(binanceKline.getOpenPrice().longValue(), bar.getOpenPrice().longValue());
        assertEquals(binanceKline.getClosePrice().longValue(), bar.getClosePrice().longValue());
//        assertEquals(binanceKline.getOpenTime(), bar.getEndTime().getLong(ChronoField.NANO_OF_DAY));
    }
}