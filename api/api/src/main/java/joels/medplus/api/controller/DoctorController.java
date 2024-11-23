package joels.medplus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joels.medplus.api.doctor.DataDoctor;
import joels.medplus.api.doctor.Doctor;
import joels.medplus.api.doctor.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping
	public void registerDoctor(@RequestBody DataDoctor dataDoctor) {
		doctorRepository.save(new Doctor(dataDoctor));
	}
}
