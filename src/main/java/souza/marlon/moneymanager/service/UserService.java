package souza.marlon.moneymanager.service;

import souza.marlon.moneymanager.domain.UserEntity;
import souza.marlon.moneymanager.dto.UserDto;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserEntity getUser(UUID id);
    List<UserDto> findAll();
}
