package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
@NamedEntityGraph(
        name = "brandsWithModels",
        attributeNodes = @NamedAttributeNode("models")
)
public class BrandEntity extends BaseEntity {

    private String name;
    private List<ModelEntity> models;

    public BrandEntity() {
        super();
    }

    @Column(nullable = false, unique = true)
    public String getName() {
        return name;
    }

    public BrandEntity setName(String name) {
        this.name = name;
        return this;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    public List<ModelEntity> getModels() {
        return models;
    }

    public BrandEntity setModels(List<ModelEntity> models) {
        this.models = models;
        return this;
    }
}
