package bg.softuni.mobilele.service.impl;

import bg.softuni.mobilele.model.dto.CreateOfferDto;
import bg.softuni.mobilele.model.dto.OfferSummaryDTO;
import bg.softuni.mobilele.model.entity.ModelEntity;
import bg.softuni.mobilele.model.entity.OfferEntity;
import bg.softuni.mobilele.repository.ModelRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.service.OfferService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    public OfferServiceImpl(OfferRepository offerRepository,
                            ModelRepository modelRepository,
                            ModelMapper modelMapper) {
        this.offerRepository = offerRepository;
        this.modelRepository = modelRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UUID createOffer(CreateOfferDto createOfferDto) {

        OfferEntity newOffer = modelMapper.map(createOfferDto, OfferEntity.class);
        newOffer.setUuid(UUID.randomUUID());

        ModelEntity model = modelRepository.findById(createOfferDto.getModelId()).orElseThrow(() ->
                new IllegalArgumentException("Model with id " + createOfferDto.getModelId() + " not found"));

        newOffer.setModel(model);

        newOffer = offerRepository.save(newOffer);

        return newOffer.getUuid();
    }

    @Override
    public Page<OfferSummaryDTO> getAllOffers(Pageable pageable) {

        return offerRepository.findAll(pageable)
                .map(offerEntity -> {
                    OfferSummaryDTO offerSummaryDTO = modelMapper.map(offerEntity, OfferSummaryDTO.class);

                    offerSummaryDTO.setBrand(offerEntity.getModel().getBrand().getName());
                    offerSummaryDTO.setModel(offerEntity.getModel().getName());
                    return offerSummaryDTO;
                });
    }
}
