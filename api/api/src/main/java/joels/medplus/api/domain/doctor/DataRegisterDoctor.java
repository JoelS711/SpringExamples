package joels.medplus.api.domain.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import joels.medplus.api.domain.address.DataAddress;

public record DataRegisterDoctor(@NotBlank String name,
		@NotBlank
		@Email
		String email,
		@NotBlank
		String phone,
		@NotBlank
		@Pattern(regexp = "\\d{4,6}")
		String identification,
		@NotNull
		Speciality speciality,
		@NotNull
		@Valid
		DataAddress	address) {

}