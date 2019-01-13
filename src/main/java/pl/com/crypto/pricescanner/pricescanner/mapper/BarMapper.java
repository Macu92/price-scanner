package pl.com.crypto.pricescanner.pricescanner.mapper;

import org.knowm.xchange.binance.dto.marketdata.BinanceKline;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import org.ta4j.core.num.DoubleNum;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.ZoneId;

public class BarMapper {

    public static Bar map(BinanceKline binanceKline) {
        return new BaseBar(DurationMapper.map(binanceKline.getInterval()),
                new Timestamp(binanceKline.getCloseTime() + 1).toInstant().atZone(ZoneId.systemDefault()),
                toDoubleNum(binanceKline.getOpenPrice()),
                toDoubleNum(binanceKline.getHighPrice()),
                toDoubleNum(binanceKline.getLowPrice()),
                toDoubleNum(binanceKline.getClosePrice()),
                toDoubleNum(binanceKline.getVolume()),
                toDoubleNum(BigDecimal.valueOf(binanceKline.getNumberOfTrades())));
    }

    private static Num toDoubleNum(BigDecimal bigDecimal) {
        return DoubleNum.valueOf(bigDecimal.doubleValue());
    }

    private static Num toPrecisionNum(BigDecimal bigDecimal) {
        return PrecisionNum.valueOf(bigDecimal);
    }

}
