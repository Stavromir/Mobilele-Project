package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;
import bg.softuni.mobilele.model.validation.YearNotInTheFuture;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.Objects;

public class CreateOfferDto {

    @NotEmpty
    @Size(min = 5, max = 512)
    private String description;
    @Positive
    @NotNull
    private Long modelId;
    @NotNull
    private EngineEnum engine;
    @NotNull
    private TransmissionEnum transmission;
    @NotEmpty
    private String imageUrl;
    @Positive
    @NotNull
    private Integer mileage;
    @Positive
    @NotNull
    private Integer price;
    @YearNotInTheFuture(message = "Enter correct year")
    @Positive
    @Min(1930)
    @NotNull
    private Integer year;

    public CreateOfferDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getModelId() {
        return modelId;
    }

    public void setModelId(Long modelId) {
        this.modelId = modelId;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public void setEngine(EngineEnum engine) {
        this.engine = engine;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public void setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getMileage() {
        return mileage;
    }

    public void setMileage(Integer mileage) {
        this.mileage = mileage;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
}
