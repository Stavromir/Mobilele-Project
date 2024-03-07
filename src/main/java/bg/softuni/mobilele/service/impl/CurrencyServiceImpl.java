package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.ExchangeRatesDto;
import bg.softuni.mobilele.model.entity.ExchangeRatesEntity;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import bg.softuni.mobilele.service.CurrencyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    private final ExchangeRateRepository exchangeRateRepository;

    public CurrencyServiceImpl(ExchangeRateRepository exchangeRateRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
    }

    @Override
    public void refreshRates(ExchangeRatesDto exchangeRatesDto) {

        LOGGER.info("Exchange rates received {}", exchangeRatesDto);

        BigDecimal BGN_TO_EUR = getExchangeRate(exchangeRatesDto, "BGN", "EUR").orElse(null);
        BigDecimal BGN_TO_USD = getExchangeRate(exchangeRatesDto, "BGN", "USD").orElse(null);

        if (BGN_TO_USD != null) {
            ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity()
                    .setCurrency("USD").setRate(BGN_TO_USD);
            exchangeRateRepository.save(exchangeRatesEntity);
        } else {
            LOGGER.error("Unable to get exchange rate for BGN To USD");
        }

        if (BGN_TO_EUR != null) {
            ExchangeRatesEntity exchangeRatesEntity = new ExchangeRatesEntity()
                    .setCurrency("EUR").setRate(BGN_TO_EUR);
            exchangeRateRepository.save(exchangeRatesEntity);
        } else {
            LOGGER.error("Unable to get exchange rate for BGN To EUR");
        }

        LOGGER.info("Rates refreshed...");
    }


    private static Optional<BigDecimal> getExchangeRate(ExchangeRatesDto exchangeRatesDto,
                                                        String from,
                                                        String to) {

        Objects.requireNonNull(from, "From currency cannot be null");
        Objects.requireNonNull(to, "To currency cannot be null");

        if (Objects.equals(from, to)) {
            return Optional.of(BigDecimal.ONE);
        }

        // USD -> BGN
        if (from.equals(exchangeRatesDto.base())
                && exchangeRatesDto.rates().containsKey(to)) {
            return Optional.of(exchangeRatesDto.rates().get(to));

            // BGN -> USD
        } else if (to.equals(exchangeRatesDto.base()) && exchangeRatesDto.rates().containsKey(from)) {
            return Optional.of(BigDecimal.ONE.divide(
                    exchangeRatesDto.rates().get(from),
                    5,
                    RoundingMode.DOWN
            ));

            // BGN -> EUR
        } else if (exchangeRatesDto.rates().containsKey(from) && exchangeRatesDto.rates().containsKey(to)) {
            return Optional.of(exchangeRatesDto.rates().get(to)
                    .divide(exchangeRatesDto.rates().get(from),
                            5,
                            RoundingMode.DOWN
                    ));
        }

        return Optional.empty();
    }
}
