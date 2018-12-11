package pl.com.crypto.pricescanner.pricescanner.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UnsupportedCandleIntervalException extends RuntimeException {

    private String intervalCode;

    public UnsupportedCandleIntervalException(String intervalCode) {
        super();
        this.intervalCode = intervalCode;
    }

    public UnsupportedCandleIntervalException(String message, String intervalCode) {
        super(message);
        this.intervalCode = intervalCode;
    }

    public UnsupportedCandleIntervalException(String message, Throwable cause, String intervalCode) {
        super(message, cause);
        this.intervalCode = intervalCode;
    }

}
