package joels.medplus.api.domain.consult;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.validation.ValidateOfConsult;
import joels.medplus.api.domain.doctor.Doctor;
import joels.medplus.api.domain.doctor.DoctorRepository;
import joels.medplus.api.domain.patient.PatientRepository;

@Service
public class ConsultationReservation {

	@Autowired
	private DoctorRepository doctorRepository;

	@Autowired
	private PatientRepository patientRepository;
	
	@Autowired
	private ConsultRepository consultRepository;
	
	@Autowired
	private List<ValidateOfConsult> validates;
	
	public void reservation(DataReserveConsult data) {
	
		if(!patientRepository.existsById(data.idPatient())) {
			throw new ValidaException("There is no patient with the identification number.");
		}
		if(data.idDoctor() != null && !doctorRepository.existsById(data.idDoctor())) {
			throw new ValidaException("There is no doctor with the identification number.");
		}
		
		validates.forEach(v -> v.validate(data));
		
		var doctor = chooseDoctor(data);
		var patient = patientRepository.findById(data.idPatient()).get();
		var consult = new Consult(null, doctor, patient, data.date());
		consultRepository.save(consult);
	}

	private Doctor chooseDoctor(DataReserveConsult data) {
		if(data.idDoctor() != null) {
			return doctorRepository.getReferenceById(data.idDoctor());
		}
		if(data.speciality() == null) {
			throw new ValidaException("It is necessary to choose a specialty when you do not choose a doctor");
		}
		return doctorRepository.chooseRandomDoctorAvailableOnTheDate(data.speciality(), data.date());
	}
}
