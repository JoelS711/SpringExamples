package joels.medplus.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import joels.medplus.api.doctor.DataRegisterDoctor;
import joels.medplus.api.doctor.Doctor;
import joels.medplus.api.doctor.DoctorListData;
import joels.medplus.api.doctor.DoctorRepository;
import joels.medplus.api.doctor.UpdateDoctorData;

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
	    //return doctorRepository.findAll(pageable).map(DoctorListData::new);
	    return doctorRepository.findByActiveTrue(pageable).map(DoctorListData::new);
	    
	}
	
	@PutMapping
	@Transactional
	public void updateDoctor(@RequestBody @Valid UpdateDoctorData updateDoctor) {
		Doctor doctor = doctorRepository.getReferenceById(updateDoctor.id());
		doctor.updateDoctorData(updateDoctor);
	}
	
	//LOGIC DELETE
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity deleteDoctor(@PathVariable Long id) {
		Doctor doctor = doctorRepository.getReferenceById(id);
		doctor.inactiveDoctor();
		return ResponseEntity.noContent().build();
	}
	
	//DELETE IN DATA BASE
	/*public void deleteDoctor(@PathVariable Long id) {
		Doctor doctor = doctorRepository.getReferenceById(id);
		doctorRepository.delete(doctor);
		
	}*/
}
