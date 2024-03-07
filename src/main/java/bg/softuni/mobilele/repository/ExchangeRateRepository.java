package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.ExchangeRatesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRatesEntity, String> {
}
