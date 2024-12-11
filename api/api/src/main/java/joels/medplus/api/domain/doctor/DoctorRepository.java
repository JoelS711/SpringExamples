package joels.medplus.api.domain.doctor;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public interface DoctorRepository extends JpaRepository<Doctor, Long>{

	Page<Doctor> findByActiveTrue(Pageable pageable);

	@Query("""
			select d from Doctors d
			where
			d.active = 1
			and
			d.speciality = :speciality
			and d.id not in(
				select c.doctor.id from Consults c
				where
				c.date = :date
			)
			order by rand()
			limit 1 
			""")
	Doctor chooseRandomDoctorAvailableOnTheDate(Speciality speciality, @NotNull @Future LocalDateTime date);

	@Query("""
			select d.active
			from Doctors d
			where
			d.id = :idDoctor
			""")
	boolean findActiveById(Long idDoctor);
	
}
