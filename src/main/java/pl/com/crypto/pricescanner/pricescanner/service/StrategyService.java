package pl.com.crypto.pricescanner.pricescanner.service;

import org.springframework.stereotype.Service;
import org.ta4j.core.BaseStrategy;
import org.ta4j.core.Rule;
import org.ta4j.core.Strategy;

@Service
public class StrategyService {

    public Strategy crateNewStrategy(Rule enterRule, Rule exitRue) {
        return new BaseStrategy(enterRule, exitRue);
    }

    public Strategy joinStrategies(Strategy first, Strategy... strategies) {
        Strategy finalStrategy = first;
        for (Strategy strategy : strategies) {
            finalStrategy = finalStrategy.and(strategy);
        }
        return finalStrategy;
    }

    public Strategy sumStrategies(Strategy first, Strategy... strategies) {
        Strategy finalStrategy = first;
        for (Strategy strategy : strategies) {
            finalStrategy = finalStrategy.or(strategy);
        }
        return finalStrategy;
    }
}
