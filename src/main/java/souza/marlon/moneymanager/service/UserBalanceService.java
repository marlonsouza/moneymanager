package souza.marlon.moneymanager.service;

import souza.marlon.moneymanager.dto.TransactionDto;
import souza.marlon.moneymanager.dto.UserBalanceDto;
import souza.marlon.moneymanager.dto.UserDto;

import java.math.BigDecimal;

public interface UserBalanceService {
    void updateBalance(TransactionDto transaction);

    boolean haveBalance(UserDto payer, BigDecimal value);

    UserBalanceDto createBalance(UserBalanceDto userBalanceDto);
}
