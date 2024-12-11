package joels.medplus.api.domain.consult.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.ConsultRepository;
import joels.medplus.api.domain.consult.DataReserveConsult;

@Component
public class ValidatePatientWithoutAnotherConsultationOnTheSameDay implements ValidateOfConsult{

	@Autowired
	private ConsultRepository consultRepository;
	
	public void validate(DataReserveConsult data) {
		var firstSchedule = data.date().withHour(7);
		var lastSchedule = data.date().withHour(18);
		var patientHasAnotherConsultationThatDay = consultRepository.existsByPatientIdAndDateBetween(data.idPatient(), firstSchedule, lastSchedule);
		if(patientHasAnotherConsultationThatDay) {
			throw new ValidaException("Paciente ya tiene una consulta reservada para ese dia");
		}
	}
}
