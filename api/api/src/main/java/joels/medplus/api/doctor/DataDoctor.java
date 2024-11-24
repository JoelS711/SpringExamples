package joels.medplus.api.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import joels.medplus.api.address.DataAddress;

public record DataDoctor(@NotBlank String name,
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
