package souza.marlon.moneymanager.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.moneymanager.domain.UserType;
import souza.marlon.moneymanager.dto.UserDto;
import souza.marlon.moneymanager.service.UserService;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserControllerTest {

  @InjectMocks
  private UserController userController;

  @Mock
  private UserService userService;

  @Test
  void testShouldCreateUserShouldBeCalled() {
    UserDto userDto = new UserDto(
        null,
        "11122233344",
        "Marlon",
        "Rua teste",
        "4899990000",
        "teste@teste.com.br",
        "123543",
        UserType.CUSTOMER
    );

    when(userService.createUser(any(UserDto.class))).thenReturn(userDto);

    UserDto response = userController.create(userDto);

    assertEquals(userDto.documentIdentity(), response.documentIdentity());
    assertEquals(userDto.name(), response.name());
    assertEquals(userDto.address(), response.address());
    assertEquals(userDto.phone(), response.phone());
    assertEquals(userDto.email(), response.email());
    assertEquals(userDto.type(), response.type());

    verify(userService).createUser(any(UserDto.class));

  }

  @Test
  void testShouldReturnListUserDto() {
    UserDto userDto = new UserDto(
        UUID.randomUUID(),
        "11122233344",
        "Marlon",
        "Rua teste",
        "4899990000",
        "teste@teste.com.br",
        "123543",
        UserType.CUSTOMER
    );

    when(userService.findAll()).thenReturn(List.of(userDto));

    var userDtos = userController.listAll();

    assertFalse(userDtos.isEmpty());
    assertEquals(userDto.id(), userDtos.getFirst().id());
    assertEquals(userDto.documentIdentity(), userDtos.getFirst().documentIdentity());
  }
}