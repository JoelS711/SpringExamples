package joels.medplus.api.domain.consult.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.DataReserveConsult;
import joels.medplus.api.domain.patient.PatientRepository;

@Component
public class ValidateActivePatient implements ValidateOfConsult{

	@Autowired
	private PatientRepository patientRepository;
	
	public void validate(DataReserveConsult data) {
		var patientIsActive = patientRepository.findActiveById(data.idPatient());
		if(!patientIsActive) {
			throw new ValidaException("Consultation cannot be booked with an inactive patient");
		}
	}
}
