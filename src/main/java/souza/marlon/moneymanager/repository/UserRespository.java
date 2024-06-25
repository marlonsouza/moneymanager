package souza.marlon.moneymanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import souza.marlon.moneymanager.domain.UserEntity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRespository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByDocumentIdentity(String documentIdentity);
}
