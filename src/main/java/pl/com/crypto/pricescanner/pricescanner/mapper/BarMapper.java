package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.Decimal;

import java.sql.Timestamp;
import java.time.ZoneId;

public class BarMapper {

    public static Bar map(BinanceKline binanceKline) {
        return new BaseBar(DurationMapper.map(binanceKline.getInterval()),
                new Timestamp(binanceKline.getCloseTime()).toInstant().atZone(ZoneId.systemDefault()),
                Decimal.valueOf(binanceKline.getOpenPrice().doubleValue()),
                Decimal.valueOf(binanceKline.getHighPrice().doubleValue()),
                Decimal.valueOf(binanceKline.getLowPrice().doubleValue()),
                Decimal.valueOf(binanceKline.getClosePrice().doubleValue()),
                Decimal.valueOf(binanceKline.getVolume().doubleValue())
        );
    }

}
