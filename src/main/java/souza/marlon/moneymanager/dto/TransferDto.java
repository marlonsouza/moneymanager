package souza.marlon.moneymanager.dto;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.UUID;

public record TransferDto(
        String description,
        @NotNull UUID payer,
        @NotNull UUID payee,
        @NotNull BigDecimal value)  {
}
