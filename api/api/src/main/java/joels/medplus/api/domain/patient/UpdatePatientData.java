package joels.medplus.api.domain.patient;

import jakarta.validation.constraints.NotNull;
import joels.medplus.api.domain.address.DataAddress;

public record UpdatePatientData(
		@NotNull
        Long id,
        String name,
        String phone,
        DataAddress address
		) {

}
