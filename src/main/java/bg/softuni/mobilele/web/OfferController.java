package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/offers")
public class OfferController {

    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }


    @GetMapping("/all")
    public String all() {
        return "offers";
    }

    @GetMapping("/add")
    public String add() {
        return "offer-add";
    }

    @PostMapping("/add")
    public String add(CreateOfferDto createOfferDto) {

        offerService.createOffer(createOfferDto);

        return "index";
    }

    @GetMapping("/{uuid}/details")
    public String details(@PathVariable("id") UUID uuid) {
        return "details";
    }



}
