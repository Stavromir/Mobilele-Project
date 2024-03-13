package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.ConvertRequestDTO;
import bg.softuni.mobilele.model.dto.MoneyDto;
import bg.softuni.mobilele.service.CurrencyService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/api/currency/convert")
    public MoneyDto convert (@Valid ConvertRequestDTO convertRequestDTO) {
        return currencyService.convert(convertRequestDTO);
    }

}
