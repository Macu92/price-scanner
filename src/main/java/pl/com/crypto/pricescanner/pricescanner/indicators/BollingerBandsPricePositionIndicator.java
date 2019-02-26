package pl.com.crypto.pricescanner.pricescanner.indicators;

import org.ta4j.core.indicators.AbstractIndicator;
import org.ta4j.core.indicators.SMAIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsLowerIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsMiddleIndicator;
import org.ta4j.core.indicators.bollinger.BollingerBandsUpperIndicator;
import org.ta4j.core.indicators.helpers.ClosePriceIndicator;
import org.ta4j.core.indicators.statistics.StandardDeviationIndicator;
import org.ta4j.core.num.Num;
import org.ta4j.core.num.PrecisionNum;

public class BollingerBandsPricePositionIndicator extends AbstractIndicator<Num> {

    private BollingerBandsLowerIndicator bollingerBandsLowerIndicator;
    private BollingerBandsMiddleIndicator bollingerBandsMiddleIndicator;
    private BollingerBandsUpperIndicator bollingerBandsUpperIndicator;

    public BollingerBandsPricePositionIndicator(ClosePriceIndicator indicator) {
        super(indicator.getTimeSeries());
        SMAIndicator smaIndicator = new SMAIndicator(indicator, indicator.getTimeSeries().getBarCount());
        StandardDeviationIndicator standardDeviationIndicator = new StandardDeviationIndicator(indicator, indicator.getTimeSeries().getBarCount());
        this.bollingerBandsMiddleIndicator = new BollingerBandsMiddleIndicator(smaIndicator);
        this.bollingerBandsLowerIndicator = new BollingerBandsLowerIndicator(this.bollingerBandsMiddleIndicator, standardDeviationIndicator);
        this.bollingerBandsUpperIndicator = new BollingerBandsUpperIndicator(this.bollingerBandsMiddleIndicator, standardDeviationIndicator);
    }

    public BollingerBandsPricePositionIndicator(
            BollingerBandsLowerIndicator bollingerBandsLowerIndicator,
            BollingerBandsMiddleIndicator bollingerBandsMiddleIndicator,
            BollingerBandsUpperIndicator bollingerBandsUpperIndicator) {
        super(bollingerBandsMiddleIndicator.getTimeSeries());
        this.bollingerBandsLowerIndicator = bollingerBandsLowerIndicator;
        this.bollingerBandsMiddleIndicator = bollingerBandsMiddleIndicator;
        this.bollingerBandsUpperIndicator = bollingerBandsUpperIndicator;
    }

    @Override
    public Num getValue(int i) {
        Num price = this.getTimeSeries().getLastBar().getClosePrice();
        Num bbmValue = this.bollingerBandsMiddleIndicator.getValue(i);
        Num bbuValue = this.bollingerBandsUpperIndicator.getValue(i);
        Num bblValue = this.bollingerBandsLowerIndicator.getValue(i);
        System.out.println("Price: " + price + " BbmValue: " + bbmValue + " bbuValue " + bbuValue + " bblValue: " + bblValue);
        if (price.isGreaterThanOrEqual(bbmValue)) {
            Num priceBand = price.minus(bbmValue);
            Num upperBand = bbuValue.minus(bbmValue);
            Num pricePosition = priceBand.dividedBy(upperBand);
            return pricePosition.multipliedBy(PrecisionNum.valueOf(100));
        } else {
            Num priceBand = bbmValue.minus(price);
            Num lowerBand = bbmValue.minus(bblValue);
            Num pricePosition = priceBand.dividedBy(lowerBand);
            return pricePosition.multipliedBy(PrecisionNum.valueOf(-100));
        }
    }
}
