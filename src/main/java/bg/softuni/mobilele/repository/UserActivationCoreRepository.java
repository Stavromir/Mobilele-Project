package bg.softuni.mobilele.repository;

import bg.softuni.mobilele.model.entity.UserActivationCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserActivationCoreRepository extends JpaRepository<UserActivationCodeEntity, Long> {
}
