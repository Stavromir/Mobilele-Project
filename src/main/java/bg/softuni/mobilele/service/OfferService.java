package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.CreateOfferDto;

import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDto createOfferDto);
}
