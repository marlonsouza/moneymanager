package souza.marlon.moneymanager.controller;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.service.UserBalanceService;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@ExtendWith(MockitoExtension.class)
class BalanceControllerTest {

  @InjectMocks
  private BalanceController balanceController;

  @Mock
  private UserBalanceService balanceService;

  @Test
  @DisplayName("Shoudl call user service to create balance")
  void testShouldCalledUserServiceOnCreateBalanceSuccess() {
    var userBalanceDto = getUserBalanceDto(1000);
    balanceController.createBalance(userBalanceDto);

    Mockito.verify(balanceService).createBalance(userBalanceDto);
  }

  @Test
  @DisplayName("Should return list of UserBalanceDto")
  void testShouldReturnListBalanceSuccess() {
    Mockito.when(balanceService.getBalance())
        .thenReturn(List.of(
            getUserBalanceDto(1000),
            getUserBalanceDto(2000)));

    var balanceList = balanceController.getBalanceList();

    Assertions.assertEquals(1000, balanceList.get(0).value().doubleValue());
    Assertions.assertEquals(2000, balanceList.get(1).value().doubleValue());
  }

  private static @NotNull UserBalanceDto getUserBalanceDto(int val) {
    return new UserBalanceDto(UUID.randomUUID(), BigDecimal.valueOf(val));
  }
}