package souza.marlon.moneymanager.service;

import jakarta.validation.constraints.NotNull;
import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.UserBalanceDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UserBalanceService {
    void updateBalance(TransactionDto transaction);

    boolean haveBalance(@NotNull UUID payer, BigDecimal value);

    UserBalanceDto createBalance(UserBalanceDto userBalanceDto);

    List<UserBalanceDto> getBalance();
}
