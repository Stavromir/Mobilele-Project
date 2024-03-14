package bg.softuni.mobilele.web;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.model.dto.OfferDetailDTO;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.service.BrandService;
import bg.softuni.mobilele.service.OfferService;
import bg.softuni.mobilele.service.exception.ObjectNotFoundException;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/offer")
public class OfferController {

    private final OfferService offerService;
    private final BrandService brandService;

    public OfferController(OfferService offerService,
                           BrandService brandService) {
        this.offerService = offerService;
        this.brandService = brandService;
    }


    @ModelAttribute("engines")
    public EngineEnum[] engines() {
        return EngineEnum.values();
    }

    @GetMapping("/add")
    public String add(Model model) {

        if (!model.containsAttribute("createOfferDto")){
            model.addAttribute("createOfferDto", new CreateOfferDto());
        }

        model.addAttribute("brands", brandService.getAllBrands());

        return "offer-add";
    }

    @PostMapping("/add")
    public String add(@Valid CreateOfferDto createOfferDto,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      @AuthenticationPrincipal UserDetails seller) {

        if (bindingResult.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("createOfferDto", createOfferDto)
                    .addFlashAttribute("org.springframework.validation.BindingResult.createOfferDto",
                            bindingResult);

            return "redirect:add";
        }

        UUID newOfferUUID = offerService.createOffer(createOfferDto, seller);

        return "redirect:/offer/" + newOfferUUID;
    }

    @GetMapping("/{uuid}")
    public String details(@PathVariable("uuid") UUID uuid, Model model,
                          @AuthenticationPrincipal UserDetails principal) {

        OfferDetailDTO offerDetailDTO = offerService
                .getOfferDetail(uuid, principal)
                .orElseThrow(() -> new ObjectNotFoundException("Offer with " + uuid + " not found"));

        model.addAttribute("offer", offerDetailDTO);

        return "details";
    }


    @PreAuthorize("@offerServiceImpl.isOwner(#uuid, #principal)")
    @DeleteMapping("/{uuid}")
    public String delete(@PathVariable("uuid") UUID uuid,
                         @AuthenticationPrincipal UserDetails principal) {

        offerService.deleteOffer(uuid);
        return "redirect:/offers/all";

    }



}
