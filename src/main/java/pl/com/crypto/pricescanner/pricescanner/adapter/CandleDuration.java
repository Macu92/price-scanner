package pl.com.crypto.pricescanner.pricescanner.adapter;

import lombok.Getter;

import java.time.Duration;

@Getter
public enum CandleDuration {
    m1("1m", Duration.ofMinutes(1)),
    m3("3m", Duration.ofMinutes(3)),
    m5("5m", Duration.ofMinutes(5)),
    m15("15m", Duration.ofMinutes(15)),
    m30("30m", Duration.ofMinutes(30)),
    h1("1h", Duration.ofHours(1)),
    h2("2h", Duration.ofHours(2)),
    h4("4h", Duration.ofHours(4)),
    h6("6h", Duration.ofHours(6)),
    h8("8h", Duration.ofHours(8)),
    h12("12h", Duration.ofHours(12)),
    d1("1d", Duration.ofDays(1)),
    d3("3d", Duration.ofDays(3)),
    w1("1w", Duration.ofDays(7)),
    M1("1M", Duration.ofDays(30));

    private final String code;
    private final Duration duration;

    private CandleDuration(String code, Duration duration) {
        this.code = code;
        this.duration = duration;
    }

}
