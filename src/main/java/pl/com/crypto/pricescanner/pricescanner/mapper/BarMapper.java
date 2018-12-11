package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.Decimal;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;

public class BarMapper {

    public static Bar map(BinanceKline binanceKline) {
        return new BaseBar(DurationMapper.map(binanceKline.getInterval()),
                new Timestamp(binanceKline.getCloseTime()).toInstant().atZone(ZoneId.systemDefault()),
                toDecimal(binanceKline.getOpenPrice()),
                toDecimal(binanceKline.getHighPrice()),
                toDecimal(binanceKline.getLowPrice()),
                toDecimal(binanceKline.getClosePrice()),
                toDecimal(binanceKline.getVolume()));
    }

    private static Decimal toDecimal(BigDecimal bigDecimal) {
        return Decimal.valueOf(bigDecimal.doubleValue());
    }

}
