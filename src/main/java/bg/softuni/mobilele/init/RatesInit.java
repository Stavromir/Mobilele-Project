package bg.softuni.mobilele.init;

import bg.softuni.mobilele.config.OpenExchangeRateConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RatesInit implements CommandLineRunner {


    private final OpenExchangeRateConfig openExchangeRateConfig;
    private final RestTemplate restTemplate;

    public RatesInit(OpenExchangeRateConfig openExchangeRateConfig,
                     RestTemplate restTemplate) {
        this.openExchangeRateConfig = openExchangeRateConfig;
        this.restTemplate = restTemplate;
    }


    @Override
    public void run(String... args) throws Exception {


        String openExchangeRateUrl =
                new StringBuilder()
                        .append(openExchangeRateConfig.getSchema())
                        .append("://")
                        .append(openExchangeRateConfig.getHost());

        restTemplate.getForObject()

    }
}
