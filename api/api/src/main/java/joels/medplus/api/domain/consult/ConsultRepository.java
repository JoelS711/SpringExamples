package joels.medplus.api.domain.consult;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultRepository extends JpaRepository <Consult, Long>{

	boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstSchedule,
			LocalDateTime lastSchedule);

	boolean existsByDoctorIdAndDate(Long idDoctor,LocalDateTime date);

	
}
