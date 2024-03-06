package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ExchangeRatesDto;

public interface CurrencyService {

    void refreshRates(ExchangeRatesDto exchangeRatesDto);
}
