package pl.com.crypto.pricescanner.pricescanner.indicators;

import org.ta4j.core.TimeSeries;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

import java.time.Duration;

public class PercentagePriceChangeInTimeIndicator extends PriceChangeInTimeIndicator {


    public PercentagePriceChangeInTimeIndicator(TimeSeries series, Duration duration) {
        super(series, duration);
    }

    @Override
    public Num getValue(int i) {
        Num priceChange = super.getValue(i);
        return priceChange.dividedBy(
                this.getTimeSeries().getBar(i).getClosePrice()).multipliedBy(PrecisionNum.valueOf(100));
    }
}
