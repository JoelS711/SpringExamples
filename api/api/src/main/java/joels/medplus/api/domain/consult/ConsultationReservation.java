package joels.medplus.api.domain.consult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void reservation(DataReserveConsult data) {
	
		var doctor = doctorRepository.findById(data.idDoctor()).get();
		var patient = patientRepository.findById(data.idPatient()).get();
		var consult = new Consult(null, doctor, patient, data.date());
		consultRepository.save(consult);
	}
}
