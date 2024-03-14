package bg.softuni.mobilele.model.dto;

import bg.softuni.mobilele.model.enums.EngineEnum;
import bg.softuni.mobilele.model.enums.TransmissionEnum;

import java.math.BigDecimal;

public class OfferDetailDTO {

    private String uuid;
    private String brand;
    private String model;
    private Integer year;
    private Long mileage;
    private BigDecimal price;
    private EngineEnum engine;
    private TransmissionEnum transmission;
    private String imageUrl;
    private String seller;
    private boolean viewerIsOwner;

    public OfferDetailDTO() {
    }

    public String getUuid() {
        return uuid;
    }

    public OfferDetailDTO setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public OfferDetailDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferDetailDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferDetailDTO setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Long getMileage() {
        return mileage;
    }

    public OfferDetailDTO setMileage(Long mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferDetailDTO setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferDetailDTO setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferDetailDTO setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferDetailDTO setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String summary() {
        return this.brand + " " + this.model + ", " + this.year;
    }

    public boolean isViewerIsOwner() {
        return viewerIsOwner;
    }

    public OfferDetailDTO setViewerIsOwner(boolean viewerIsOwner) {
        this.viewerIsOwner = viewerIsOwner;
        return this;
    }

    public String getSeller() {
        return seller;
    }

    public OfferDetailDTO setSeller(String seller) {
        this.seller = seller;
        return this;
    }
}
