package pl.com.crypto.pricescanner.pricescanner.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.knowm.xchange.currency.CurrencyPair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.com.crypto.pricescanner.pricescanner.adapter.CandleDuration;
import pl.com.crypto.pricescanner.pricescanner.service.CurrencyPairService;
import pl.com.crypto.pricescanner.pricescanner.service.MarketService;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/test-api")
public class TestController {

    private final MarketService marketService;
    private final CurrencyPairService currencyPairService;

    @GetMapping(value = "/start")
    public HttpStatus startPriceScanning() {
        log.info("STARTED");
        currencyPairService.streamTicker(CurrencyPair.BTC_USDT);
        marketService.createMarket(CurrencyPair.BTC_USDT, CandleDuration.m1);
//        marketService.createMarket(CurrencyPair.BTC_USDT, CandleDuration.m5);
//        currencyPairService.streamTicker(CurrencyPair.ETH_BTC);
//        marketService.createMarket(CurrencyPair.ETH_BTC, CandleDuration.m5);
        return HttpStatus.OK;
    }


    @GetMapping(value = "/stop")
    public HttpStatus stop() {
        marketService.disconnectAllMarkets();
        return HttpStatus.OK;
    }
}

