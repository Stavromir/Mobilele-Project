package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.ExchangeRatesDto;
import bg.softuni.mobilele.model.entity.ExchangeRatesEntity;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import bg.softuni.mobilele.service.CurrencyService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@SpringBootTest
class CurrencyServiceImplTestIT {

    @Autowired
    private CurrencyService currencyServiceToTest;

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;


    @BeforeEach
    void setUp() {
        exchangeRateRepository.deleteAll();
    }

    @AfterEach
    void tearDown() {
        exchangeRateRepository.deleteAll();
    }


    @ParameterizedTest(name = "Conversion BGN/USD exRate {0}, expected {1}" )
    @MethodSource("testDateBGN_TO_USD")
    void testBGN_TO_USDD(Double exchangeRate, Double exchangeValue) {

        ExchangeRatesDto exchangeRatesDto =
                new ExchangeRatesDto("USD", Map.of("BGN", BigDecimal.valueOf(exchangeRate)));

        currencyServiceToTest.refreshRates(exchangeRatesDto);

        Optional<ExchangeRatesEntity> exRateBGN_USD = exchangeRateRepository.findById("USD");

        Assertions.assertTrue(exRateBGN_USD.isPresent());

        Assertions.assertEquals(
                BigDecimal.valueOf(exchangeValue).setScale(2, RoundingMode.DOWN),
                exRateBGN_USD.map(ExchangeRatesEntity::getRate).orElseThrow()
        );
    }


    @ParameterizedTest(name = "Conversion BGN/EUR exRateBGN {0}, exRateEUR {1}, expected {2}" )
    @MethodSource("testDateBGN_TO_EUR")
    void testBGN_TO_EUR(Double exchangeRateBGN, Double exchangeRateEUR, Double exchangeValue) {

        ExchangeRatesDto exchangeRatesDto =
                new ExchangeRatesDto("USD",
                        Map.of("BGN", BigDecimal.valueOf(exchangeRateBGN),
                                "EUR", BigDecimal.valueOf(exchangeRateEUR)));

        currencyServiceToTest.refreshRates(exchangeRatesDto);

        Optional<ExchangeRatesEntity> exRateBGN_EUR = exchangeRateRepository.findById("EUR");

        Assertions.assertTrue(exRateBGN_EUR.isPresent());

        Assertions.assertEquals(
                BigDecimal.valueOf(exchangeValue).setScale(2, RoundingMode.DOWN),
                exRateBGN_EUR.map(ExchangeRatesEntity::getRate).orElseThrow()
        );
    }

    private static Stream<Arguments> testDateBGN_TO_EUR() {
        return Stream.of(
                Arguments.of(1.840515, 0.937668, 0.51),
                Arguments.of(1.666515, 0.937668, 0.56),
                Arguments.of(1.0, 1.0, 1.0)
        );
    }

    private static Stream<Arguments> testDateBGN_TO_USD() {
        return Stream.of(
                Arguments.of(1.840515, 0.54),
                Arguments.of(1.740715, 0.57),
                Arguments.of(1.230515, 0.81),
                Arguments.of(1.0, 1.0)
        );
    }
//    0.937668
}