package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.ConvertRequestDTO;
import bg.softuni.mobilele.model.dto.MoneyDto;
import bg.softuni.mobilele.service.CurrencyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyRestController {

    private final CurrencyService currencyService;

    public CurrencyRestController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @Operation(summary = "Convert BGN to target currency.")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Returned when successfully returned currency.",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = MoneyDto.class)
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "There is no information about this currency.",
                    content = @Content
            )
    }
    )
    @Parameter(name = "target", description = "The currency", required = true)
    @Parameter(name = "amount", description = "The amount to convert", required = true)
    @GetMapping("/api/currency/convert")
    public MoneyDto convert (@Valid ConvertRequestDTO convertRequestDTO) {
        return currencyService.convert(convertRequestDTO);
    }

}
