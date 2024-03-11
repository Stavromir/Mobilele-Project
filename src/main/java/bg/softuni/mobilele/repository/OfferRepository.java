package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.OfferEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

    Optional<OfferEntity> findByUuid(UUID uuid);

    void deleteByUuid(UUID uuid);

}
