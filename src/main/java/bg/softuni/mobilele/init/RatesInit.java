package bg.softuni.mobilele.init;

import bg.softuni.mobilele.config.OpenExchangeRateConfig;
import bg.softuni.mobilele.model.dto.ExchangeRatesDto;
import bg.softuni.mobilele.service.CurrencyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class RatesInit implements CommandLineRunner {


    private final OpenExchangeRateConfig openExchangeRateConfig;
    private final RestTemplate restTemplate;
    private final CurrencyService currencyService;

    public RatesInit(OpenExchangeRateConfig openExchangeRateConfig,
                     RestTemplate restTemplate,
                     CurrencyService currencyService) {
        this.openExchangeRateConfig = openExchangeRateConfig;
        this.restTemplate = restTemplate;
        this.currencyService = currencyService;
    }


    @Override
    public void run(String... args) throws Exception {

        if (openExchangeRateConfig.getEnabled()) {

            String openExchangeRateUrl =
                    openExchangeRateConfig.getSchema() +
                            "://" +
                            openExchangeRateConfig.getHost() +
                            openExchangeRateConfig.getPath() +
                            "?app_id={app_id}&symbols={symbols}";

            Map<String, String> requestParam = Map.of(
                    "app_id", openExchangeRateConfig.getAppId(),
                    "symbols", String.join(",", openExchangeRateConfig.getSymbols())
            );


            ExchangeRatesDto exchangeRateDto = restTemplate
                    .getForObject(openExchangeRateUrl, ExchangeRatesDto.class, requestParam);

            currencyService.refreshRates(exchangeRateDto);

        }

    }
}
