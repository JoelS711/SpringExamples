package joels.medplus.api.domain.consult;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import joels.medplus.api.domain.doctor.Speciality;

public record DataReserveConsult(Long idDoctor, @NotNull Long idPatient, @NotNull @Future LocalDateTime date, Speciality speciality) {

}
