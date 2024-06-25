package souza.marlon.moneymanager.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import souza.marlon.moneymanager.domain.UserEntity;
import souza.marlon.moneymanager.dto.UserDto;
import souza.marlon.moneymanager.exception.BadRequestException;
import souza.marlon.moneymanager.mapper.UserConverterImpl;
import souza.marlon.moneymanager.repository.UserRespository;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private final UserRespository userRespository;

    private final UserConverterImpl userConverter;

    @Autowired
    public UserServiceImpl(UserRespository userRespository, UserConverterImpl userConverter) {
        this.userRespository = userRespository;
        this.userConverter = userConverter;
    }

    @Override
    @Transactional
    public UserDto createUser(UserDto userDto) {
        var documentIdentity = userDto.documentIdentity();
        if (userRespository.findByDocumentIdentity(documentIdentity)
                .isPresent()) {
            throw new BadRequestException(String.format("User with documentIdentity already exists %s", documentIdentity));
        }

        var saved = userRespository.save(userConverter.toEntity(userDto));
        return userConverter.toDto(saved);
    }

    @Override
    public UserEntity getUser(UUID id){
        return userRespository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }

    public List<UserDto> findAll(){
        return userRespository.findAll().stream().map(userConverter::toDto).toList();
    }
}
