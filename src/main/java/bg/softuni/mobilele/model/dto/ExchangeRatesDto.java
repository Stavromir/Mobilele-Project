package bg.softuni.mobilele.model.dto;

import java.math.BigDecimal;
import java.util.Map;

public record ExchangeRatesDto(String base, Map<String, BigDecimal> rates) {
}
