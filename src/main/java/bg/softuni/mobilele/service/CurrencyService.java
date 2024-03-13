package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.ConvertRequestDTO;
import bg.softuni.mobilele.model.dto.ExchangeRatesDto;
import bg.softuni.mobilele.model.dto.MoneyDto;

public interface CurrencyService {

    void refreshRates(ExchangeRatesDto exchangeRatesDto);

    MoneyDto convert(ConvertRequestDTO convertRequestDTO);
}
