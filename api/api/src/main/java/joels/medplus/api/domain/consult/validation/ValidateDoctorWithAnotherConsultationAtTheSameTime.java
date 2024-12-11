package joels.medplus.api.domain.consult.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.ConsultRepository;
import joels.medplus.api.domain.consult.DataReserveConsult;

@Component
public class ValidateDoctorWithAnotherConsultationAtTheSameTime implements ValidateOfConsult{

	@Autowired
	private ConsultRepository consultRepository;
	
	public void validate(DataReserveConsult data) {
	
		var doctorHasAnotherConsultationAtTheSameTime = consultRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
		if(doctorHasAnotherConsultationAtTheSameTime) {
			throw new ValidaException("Doctor already has another consultation on that same date and time");
		}
	}
}
