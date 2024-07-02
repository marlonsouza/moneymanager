package souza.marlon.moneymanager.converter;

import org.springframework.stereotype.Component;
import souza.marlon.moneymanager.documents.UserBalanceDocument;
import souza.marlon.moneymanager.dto.UserBalanceDto;

@Component
public class UserBalanceConverterImpl implements Converter<UserBalanceDto, UserBalanceDocument> {
    @Override
    public UserBalanceDto toDto(UserBalanceDocument entity) {
        return new UserBalanceDto(
            entity.getId(),
            entity.getValue()
        );
    }

    @Override
    public UserBalanceDocument toEntity(UserBalanceDto dto) {
        return new UserBalanceDocument(dto.userId(), dto.value());
    }
}
