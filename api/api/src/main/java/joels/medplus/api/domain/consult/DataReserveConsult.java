package joels.medplus.api.domain.consult;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record DataReserveConsult(Long idDoctor, @NotNull Long idPatient, @NotNull @Future LocalDateTime date) {

}
