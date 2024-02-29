package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.BrandEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BrandRepository extends JpaRepository<BrandEntity, Long> {

    @EntityGraph(
            value = "brandsWithModels",
            attributePaths = "models"
    )
    @Query("SELECT b FROM BrandEntity b")
    List<BrandEntity> findAllByOrderByName();
}
