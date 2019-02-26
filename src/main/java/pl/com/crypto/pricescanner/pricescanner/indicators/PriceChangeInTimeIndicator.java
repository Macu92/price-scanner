package pl.com.crypto.pricescanner.pricescanner.indicators;

import org.ta4j.core.Bar;
import org.ta4j.core.TimeSeries;
import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.num.Num;

import java.time.Duration;
import java.time.ZonedDateTime;

public class PriceChangeInTimeIndicator extends AbstractIndicator<Num> {

    private Duration duration;

    public PriceChangeInTimeIndicator(TimeSeries series, Duration duration) {
        super(series);
        this.duration = duration;
    }

    private Duration defineTimeSeriesDuration(TimeSeries timeSeries) throws Exception {
        int lastIndex = timeSeries.getEndIndex();
        if (timeSeries.getBar(0).getTimePeriod().equals(timeSeries.getBar(lastIndex / 3).getTimePeriod()) &&
                timeSeries.getBar(0).getTimePeriod().equals(timeSeries.getBar((lastIndex / 3) * 2).getTimePeriod())) {
            return timeSeries.getBar(0).getTimePeriod();
        }
        throw new Exception("Duration cant be defined");
    }

    private Bar findDurationBar(Bar baseBar) {
        ZonedDateTime searchedDate = baseBar.getEndTime().minusMinutes(this.duration.toMinutes());
        return this.getTimeSeries().getBarData().stream().filter(bar -> bar.getEndTime().equals(searchedDate)).findAny().get();
    }

    @Override
    public Num getValue(int i) { //[TODO] pobiera zla cene swiecy przy 1h jest nieaktualna
        try {
            Duration seriesDuration = defineTimeSeriesDuration(this.getTimeSeries());
            if (seriesDuration.compareTo(this.duration) > 0) {
                throw new Exception("Provided period is lower than chart candle period");
            }
            System.out.println("##############################PriceChangeInDI#################");
            System.out.println(this.duration);
            System.out.println(getTimeSeries().getBar(i));
            System.out.println(findDurationBar(getTimeSeries().getBar(i)));
            return getTimeSeries().getBar(i).getClosePrice().minus(findDurationBar(getTimeSeries().getBar(i)).getClosePrice());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
