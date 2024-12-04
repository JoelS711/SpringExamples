package joels.medplus.api.domain.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import jakarta.validation.Valid;
import joels.medplus.api.domain.address.DataAddress;

public record DataRegisterPatient(
		@NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String phone,
        @NotBlank
        String identification,
        @NotNull @Valid DataAddress address
		) {

}
