package souza.marlon.moneymanager.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import souza.marlon.moneymanager.domain.TransactionEntity;
import souza.marlon.moneymanager.dto.TransactionDto;

@Component
public class TransactionConverterImpl implements Converter<TransactionDto, TransactionEntity> {

    private final UserConverterImpl userConverter;

    @Autowired
    public TransactionConverterImpl(UserConverterImpl userConverter) {
        this.userConverter = userConverter;
    }

    @Override
    public TransactionDto toDto(TransactionEntity entity) {
        return new TransactionDto(
            entity.getDescription(),
            userConverter.toDto(entity.getPayer()),
            userConverter.toDto(entity.getPayee()),
            entity.getCategory(),
            entity.getValue()
        );
    }

    @Override
    public TransactionEntity toEntity(TransactionDto dto) {
        var transactionEntity = new TransactionEntity();
        transactionEntity.setDescription(dto.description());
        transactionEntity.setPayee(userConverter.toEntity(dto.payee()));
        transactionEntity.setPayer(userConverter.toEntity(dto.payer()));
        transactionEntity.setCategory(dto.category());
        transactionEntity.setValue(dto.value());
        return transactionEntity;
    }
}
