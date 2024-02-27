package bg.softuni.mobilele.model.entity;

import bg.softuni.mobilele.model.enums.ModelCategoryEnum;
import jakarta.persistence.*;

@Entity
@Table(name = "models")
public class ModelEntity extends BaseEntity {

    private String name;
    private ModelCategoryEnum category;
    private BrandEntity brand;

    public ModelEntity() {
        super();
    }

    @Column
    public String getName() {
        return name;
    }


    public ModelEntity setName(String name) {
        this.name = name;
        return this;
    }

    @Enumerated(EnumType.STRING)
    public ModelCategoryEnum getCategory() {
        return category;
    }

    public ModelEntity setCategory(ModelCategoryEnum category) {
        this.category = category;
        return this;
    }

    @ManyToOne
    public BrandEntity getBrand() {
        return brand;
    }

    public ModelEntity setBrand(BrandEntity brand) {
        this.brand = brand;
        return this;
    }
}
