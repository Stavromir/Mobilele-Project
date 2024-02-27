package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.BrandDto;
import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService,
                           BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @GetMapping("/all")
    public String all() {
        return "offers";
    }

    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {

        List<BrandDto> allBrands = brandService.getAllBrands();

        model.addAttribute("models", allBrands);

        return "offer-add";
    }

    @PostMapping("/add")
    public String add(CreateOfferDto createOfferDto) {

        offerService.createOffer(createOfferDto);

        return "index";
    }

    @GetMapping("/{uuid}/details")
    public String details(@PathVariable("uuid") UUID uuid) {
        return "details";
    }



}
