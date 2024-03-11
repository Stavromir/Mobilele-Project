package bg.softuni.mobilele.service;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.model.dto.OfferDetailDTO;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OfferService {

    UUID createOffer(CreateOfferDto createOfferDto);

    Page<OfferSummaryDTO> getAllOffers(Pageable pageable);

    Optional<OfferDetailDTO> getOfferDetail(UUID offerUUID);

    void deleteOffer(UUID offerUUID);
}
