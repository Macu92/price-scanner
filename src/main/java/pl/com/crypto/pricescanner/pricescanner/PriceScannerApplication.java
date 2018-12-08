package pl.com.crypto.pricescanner.pricescanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PriceScannerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PriceScannerApplication.class, args);
	}
}
