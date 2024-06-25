package souza.marlon.moneymanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import souza.marlon.moneymanager.domain.UserType;

import java.util.UUID;

public record UserDto(
        UUID id,
        @NotEmpty(message = "Document identity cannot be empty") String documentIdentity,
        @NotEmpty(message = "Name cannot be empty") String name,
        String address,
        String phone,
        @NotNull @Email String email,
        @NotNull String password,
        @NotNull(message = "Type of user is requised") UserType type) {
}
