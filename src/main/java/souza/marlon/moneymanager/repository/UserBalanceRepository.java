package souza.marlon.moneymanager.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import souza.marlon.moneymanager.documents.UserBalanceDocument;

import java.util.UUID;

@Repository
public interface UserBalanceRepository extends MongoRepository<UserBalanceDocument, UUID> {
}
