package joels.medplus.api.domain.patient;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.validation.constraints.NotNull;

public interface PatientRepository extends JpaRepository<Patient, Long>{

	Page<Patient> findAllByActiveTrue(Pageable pagination);

	boolean findActiveById(Long idPatient);
}
