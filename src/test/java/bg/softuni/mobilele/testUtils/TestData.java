package bg.softuni.mobilele.testUtils;

import bg.softuni.mobilele.model.entity.ExchangeRatesEntity;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class TestData {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRatesEntity()
                        .setCurrency(currency)
                        .setRate(rate)
        );
    }

    public void cleanAllTestData() {
        exchangeRateRepository.deleteAll();
    }

}
