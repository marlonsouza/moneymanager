package souza.marlon.moneymanager.dto;

import jakarta.validation.constraints.NotNull;
import souza.marlon.moneymanager.domain.TransactionCategory;

import java.math.BigDecimal;

public record TransactionDto (
        String description,
        @NotNull UserDto payer,
        @NotNull UserDto payee,
        @NotNull TransactionCategory category,
        @NotNull BigDecimal value) {
}
