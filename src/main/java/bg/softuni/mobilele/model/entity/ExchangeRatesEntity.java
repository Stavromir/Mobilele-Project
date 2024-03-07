package bg.softuni.mobilele.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

@Entity
@Table(name = "exchange_rates")
public class ExchangeRatesEntity {


    private String currency;
    private BigDecimal rate;

    public ExchangeRatesEntity() {
    }

    @Id
    @NotNull
    public String getCurrency() {
        return currency;
    }

    public ExchangeRatesEntity setCurrency(String currency) {
        this.currency = currency;
        return this;
    }

    @NotNull
    @Column(precision = 9, scale = 6)
    public BigDecimal getRate() {
        return rate;
    }

    public ExchangeRatesEntity setRate(BigDecimal rate) {
        this.rate = rate;
        return this;
    }
}
