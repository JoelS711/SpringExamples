package joels.medplus.api.domain.consult.validation;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.DataReserveConsult;
import joels.medplus.api.domain.doctor.DoctorRepository;

public class ValidateActiveDoctor {

	private DoctorRepository doctorRepository;
	
	public void validate(DataReserveConsult data) {
		if(data.idDoctor() == null) {
			return;
		}
		var doctorIsActive = doctorRepository.findActiveById(data.idDoctor());
		if(!doctorIsActive) {
			throw new ValidaException("Consultation cannot be booked with an inactive doctor");
		}
	}
}
