package joels.medplus.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import joels.medplus.api.doctor.DataDoctor;
import joels.medplus.api.doctor.Doctor;
import joels.medplus.api.doctor.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping
	public void registerDoctor(@RequestBody @Valid DataDoctor dataDoctor) {
		doctorRepository.save(new Doctor(dataDoctor));
	}
	
	@GetMapping
	public List<Doctor> listDoctors(){
		return doctorRepository.findAll();
	}
}
