package souza.marlon.moneymanager.mapper;

import org.springframework.stereotype.Component;
import souza.marlon.moneymanager.dto.UserDto;
import souza.marlon.moneymanager.domain.UserEntity;

@Component
public class UserConverterImpl implements Converter<UserDto, UserEntity> {

    @Override
    public UserDto toDto(UserEntity entity) {
        return new UserDto(
            entity.getId(),
            entity.getDocumentIdentity(),
            entity.getName(),
            entity.getAddress(),
            entity.getPhone(),
            entity.getEmail(),
            "***",
            entity.getUserType()
        );
    }

    @Override
    public UserEntity toEntity(UserDto dto) {
        return new UserEntity(
            dto.id(),
            dto.documentIdentity(),
            dto.name(),
            dto.address(),
            dto.phone(),
            dto.email(),
            dto.password(),
            dto.type()
        );
    }
}
