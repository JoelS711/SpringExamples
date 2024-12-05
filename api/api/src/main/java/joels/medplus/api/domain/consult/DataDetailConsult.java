package joels.medplus.api.domain.consult;

import java.time.LocalDateTime;

public record DataDetailConsult(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {

}
