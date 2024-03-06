package bg.softuni.mobilele.init;

import bg.softuni.mobilele.config.OpenExchangeRateConfig;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class RatesInit implements CommandLineRunner {


    private final OpenExchangeRateConfig openExchangeRateConfig;

    public RatesInit(OpenExchangeRateConfig openExchangeRateConfig) {
        this.openExchangeRateConfig = openExchangeRateConfig;
    }


    @Override
    public void run(String... args) throws Exception {

        System.out.println(openExchangeRateConfig);

    }
}
