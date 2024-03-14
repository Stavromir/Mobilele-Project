package bg.softuni.mobilele.testUtils;

import bg.softuni.mobilele.model.entity.*;
import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.model.enums.UserRoleEnum;
import bg.softuni.mobilele.repository.ExchangeRateRepository;
import bg.softuni.mobilele.repository.OfferRepository;
import bg.softuni.mobilele.repository.UserRepository;
import bg.softuni.mobilele.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Component
public class TestDataUtil {

    @Autowired
    private ExchangeRateRepository exchangeRateRepository;

    @Autowired
    private OfferRepository offerRepository;

    public OfferEntity createTestOffer(UserEntity owner) {

        BrandEntity brandEntity = new BrandEntity()
                .setName("Test brand")
                .setModels(List.of(
                        new ModelEntity().setName("Test model ONE"),
                        new ModelEntity().setName("Test model TWO")
                ));

        OfferEntity offer = new OfferEntity()
                .setModel(brandEntity.getModels().getFirst())
                .setImageUrl("http://www.google.bg")
                .setPrice(BigDecimal.TWO)
                .setYear(2020)
                .setDescription("Test description")
                .setEngine(EngineEnum.PETROL)
                .setMileage(100000)
                .setTransmission(TransmissionEnum.MANUAL)
                .setSeller(owner)
                .setUuid(UUID.randomUUID());

        return offerRepository.save(offer);
    }


    public void createExchangeRate(String currency, BigDecimal rate) {
        exchangeRateRepository.save(
                new ExchangeRatesEntity()
                        .setCurrency(currency)
                        .setRate(rate)
        );
    }

    public void cleanAllTestData() {
        exchangeRateRepository.deleteAll();
        offerRepository.deleteAll();
    }

}
