package joels.medplus.api.domain.consult.validation;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.ConsultRepository;
import joels.medplus.api.domain.consult.DataReserveConsult;

public class ValidateDoctorWithAnotherConsultationAtTheSameTime {

	private ConsultRepository consultRepository;
	
	public void validate(DataReserveConsult data) {
	
		var doctorHasAnotherConsultationAtTheSameTime = consultRepository.existsByDoctorIdAndDate(data.idDoctor(), data.date());
		if(doctorHasAnotherConsultationAtTheSameTime) {
			throw new ValidaException("Doctor already has another consultation on that same date and time");
		}
	}
}
