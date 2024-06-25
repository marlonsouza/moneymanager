package souza.marlon.moneymanager.dto;

import java.math.BigDecimal;
import java.util.UUID;

public record UserBalanceDto(UUID userId, BigDecimal value) {
}
