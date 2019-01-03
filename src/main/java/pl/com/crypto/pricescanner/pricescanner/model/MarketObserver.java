package pl.com.crypto.pricescanner.pricescanner.model;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Data
@RequiredArgsConstructor
@Slf4j
public class MarketObserver implements Observer {

    private final Market market;

    @Override
    public void onSubscribe(Disposable disposable) {

    }

    @Override
    public void onNext(Object o) {
        log.info(String.format("Duration %s got Ticker: %s", market.getDuration(), o));
    }

    @Override
    public void onError(Throwable throwable) {

    }

    @Override
    public void onComplete() {

    }
}

