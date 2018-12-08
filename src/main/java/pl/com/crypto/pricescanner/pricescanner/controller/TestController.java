package pl.com.crypto.pricescanner.pricescanner.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@Controller
@RequestMapping("/test-api")
public class TestController {

    @RequestMapping(value = "/start", method = RequestMethod.GET)
    @ResponseBody
    HttpStatus startPriceScanning() {
        log.info("STARTED");

        return HttpStatus.OK;
    }
}

