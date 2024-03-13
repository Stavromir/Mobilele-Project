package bg.softuni.mobilele.model.dto;

import java.math.BigDecimal;

public record MoneyDto(String currency, BigDecimal amount) {
}
