package joels.medplus.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import joels.medplus.api.doctor.DataRegisterDoctor;
import joels.medplus.api.doctor.Doctor;
import joels.medplus.api.doctor.DoctorListData;
import joels.medplus.api.doctor.DoctorRepository;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@Autowired
	private DoctorRepository doctorRepository;
	
	@PostMapping
	public void registerDoctor(@RequestBody @Valid DataRegisterDoctor dataDoctor) {
		doctorRepository.save(new Doctor(dataDoctor));
	}
	
	@GetMapping
	public Page<DoctorListData> getAllDoctors(@PageableDefault(size=2) Pageable pageable){
	    return doctorRepository.findAll(pageable).map(DoctorListData::new);
	}
}
