package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class OfferSummaryDTO {


    private String uuid;
    private String brand;
    private String model;
    private Integer year;
    private Long mileage;
    private BigDecimal price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private String imageUrl;

    public OfferSummaryDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public OfferSummaryDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferSummaryDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummaryDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferSummaryDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Long getMileage() {
        return mileage;
    }

    public OfferSummaryDTO setMileage(Long mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferSummaryDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferSummaryDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferSummaryDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String summary() {
        return this.brand + " " + this.model + ", " + this.year;
    }
}
