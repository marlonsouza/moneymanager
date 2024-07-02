package souza.marlon.moneymanager.dto;

import jakarta.validation.constraints.NotNull;
import souza.marlon.moneymanager.domain.TransactionCategory;

import java.math.BigDecimal;
import java.util.UUID;

public record TransactionDto (
        String description,
        @NotNull UUID payer,
        @NotNull UUID payee,
        @NotNull TransactionCategory category,
        @NotNull BigDecimal value) {
}
