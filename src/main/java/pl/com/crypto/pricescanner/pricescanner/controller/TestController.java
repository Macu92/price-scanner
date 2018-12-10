package pl.com.crypto.pricescanner.pricescanner.controller;

import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.binance.dto.marketdata.KlineInterval;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.ta4j.core.Bar;
import pl.com.crypto.pricescanner.pricescanner.service.MarketService;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/test-api")
public class TestController {

    private final MarketService marketService;

    public TestController(MarketService marketService) {
        this.marketService = marketService;
    }

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    HttpStatus startPriceScanning() {
        log.info("STARTED");
        try {
            List<Bar> bars = marketService.getHistory(CurrencyPair.BTC_USDT, Duration.ofMinutes(1));
            bars.forEach(bar -> System.out.println(bar.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }
}

