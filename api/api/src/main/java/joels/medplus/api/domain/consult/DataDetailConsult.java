package joels.medplus.api.domain.consult;

import java.time.LocalDateTime;

public record DataDetailConsult(Long id, Long idDoctor, Long idPatient, LocalDateTime date) {

	public DataDetailConsult(Consult consult) {
		this(consult.getId(), consult.getDoctor().getId(), consult.getPatient().getId(), consult.getDate());
	}

}
