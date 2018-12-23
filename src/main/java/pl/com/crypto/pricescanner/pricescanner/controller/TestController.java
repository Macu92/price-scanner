package pl.com.crypto.pricescanner.pricescanner.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.ta4j.core.Bar;
import org.ta4j.core.BaseBar;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.service.BarService;
import pl.com.crypto.pricescanner.pricescanner.service.MarketService;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test-api")
public class TestController {

    private final BarService barService;

    @GetMapping(value = "/start")
    HttpStatus startPriceScanning() {
        log.info("STARTED");
        try {
            List<Bar> bars = barService.getHistory(CurrencyPair.BTC_USDT, CandleDuration.m1.getDuration());
            bars.forEach(bar -> System.out.println(((BaseBar)bar).getBeginTime().toString() +" "+bar.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }
}

