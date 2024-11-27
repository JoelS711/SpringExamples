package joels.medplus.api.doctor;

import jakarta.validation.constraints.NotNull;
import joels.medplus.api.address.DataAddress;

public record UpdateDoctorData(@NotNull Long id, String name, String identification, DataAddress address) {

	
}
