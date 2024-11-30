package joels.medplus.api.domain.doctor;

import jakarta.validation.constraints.NotNull;
import joels.medplus.api.domain.address.DataAddress;

public record UpdateDoctorData(@NotNull Long id, String name, String identification, DataAddress address) {

	
}
