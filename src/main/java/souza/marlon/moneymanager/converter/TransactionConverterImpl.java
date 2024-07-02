package souza.marlon.moneymanager.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import souza.marlon.moneymanager.domain.TransactionEntity;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.repository.UserRespository;

@Component
public class TransactionConverterImpl implements Converter<TransactionDto, TransactionEntity> {

    private final UserRespository userRespository;

    @Autowired
    public TransactionConverterImpl(UserConverterImpl userConverter, UserRespository userRespository) {
        this.userRespository = userRespository;
    }

    @Override
    public TransactionDto toDto(TransactionEntity entity) {
        return new TransactionDto(
            entity.getDescription(),
            entity.getPayer().getId(),
            entity.getPayee().getId(),
            entity.getCategory(),
            entity.getValue()
        );
    }

    @Override
    public TransactionEntity toEntity(TransactionDto dto) {
        var transactionEntity = new TransactionEntity();
        transactionEntity.setDescription(dto.description());
        transactionEntity.setPayee(userRespository.findById(dto.payee()).orElse(null));
        transactionEntity.setPayer(userRespository.findById(dto.payer()).orElse(null));
        transactionEntity.setCategory(dto.category());
        transactionEntity.setValue(dto.value());
        return transactionEntity;
    }
}
