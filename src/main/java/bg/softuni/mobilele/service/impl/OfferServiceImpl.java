package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;

    public OfferServiceImpl(OfferRepository offerRepository) {
        this.offerRepository = offerRepository;
    }

    @Override
    public UUID createOffer(CreateOfferDto createOfferDto) {
        //todo
        throw new UnsupportedOperationException("Coming soon!");
    }
}
