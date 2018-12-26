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
import pl.com.crypto.pricescanner.pricescanner.model.Market;
import pl.com.crypto.pricescanner.pricescanner.service.CurrencyPairService;
import pl.com.crypto.pricescanner.pricescanner.service.MarketService;

import java.io.IOException;
import java.util.List;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test-api")
public class TestController {

    private final CurrencyPairService currencyPairService;
    private final MarketService marketService;

    @GetMapping(value = "/start")
    public HttpStatus startPriceScanning() {
        log.info("STARTED");
        try {
            List<Bar> bars = currencyPairService.getHistory(CurrencyPair.BTC_USDT, CandleDuration.m1.getDuration());
            bars.forEach(bar -> System.out.println(((BaseBar) bar).getBeginTime().toString() + " " + bar.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return HttpStatus.OK;
    }

    @GetMapping(value = "/test")
    public HttpStatus test() {
        marketService.addMarket(CurrencyPair.BTC_USDT, CandleDuration.m1.getDuration());
        marketService.addMarket(CurrencyPair.ETH_BTC, CandleDuration.m5.getDuration());
        return HttpStatus.OK;
    }

    @GetMapping(value = "/stop")
    public HttpStatus stop() {
        marketService.disconnectAllMarkets();
        return HttpStatus.OK;
    }
}

