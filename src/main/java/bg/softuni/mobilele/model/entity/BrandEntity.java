package bg.softuni.mobilele.model.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "brands")
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

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.EAGER)
    public List<ModelEntity> getModels() {
        return models;
    }

    public void setModels(List<ModelEntity> models) {
        this.models = models;
    }
}
