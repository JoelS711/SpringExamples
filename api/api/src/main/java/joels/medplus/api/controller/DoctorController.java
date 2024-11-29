package joels.medplus.api.controller;


import java.net.URI;

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
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import joels.medplus.api.address.DataAddress;
import joels.medplus.api.doctor.DataAnswerDoctor;
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
	public ResponseEntity<DataAnswerDoctor> registerDoctor(@RequestBody @Valid DataRegisterDoctor dataDoctor, UriComponentsBuilder uriComponentsBuilder) {
		Doctor doctor = doctorRepository.save(new Doctor(dataDoctor));
		DataAnswerDoctor dataAnswerDoctor = new DataAnswerDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(),
				doctor.getIdentification(), new DataAddress(doctor.getAddress().getStreet(),doctor.getAddress().getDistrict(),
						doctor.getAddress().getCity(),doctor.getAddress().getNumber(),doctor.getAddress().getComplement()));
		URI url = uriComponentsBuilder.path("/doctor/{id}").buildAndExpand(doctor.getId()).toUri();
		return ResponseEntity.created(url).body(dataAnswerDoctor);
	}
	
	@GetMapping
	public ResponseEntity<Page<DoctorListData>> getAllDoctors(@PageableDefault(size=2) Pageable pageable){
	    //return doctorRepository.findAll(pageable).map(DoctorListData::new);
	    return ResponseEntity.ok(doctorRepository.findByActiveTrue(pageable).map(DoctorListData::new));
	    
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity updateDoctor(@RequestBody @Valid UpdateDoctorData updateDoctor) {
		Doctor doctor = doctorRepository.getReferenceById(updateDoctor.id());
		doctor.updateDoctorData(updateDoctor);
		return ResponseEntity.ok(new DataAnswerDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(),
				doctor.getIdentification(), new DataAddress(doctor.getAddress().getStreet(),doctor.getAddress().getDistrict(),
						doctor.getAddress().getCity(),doctor.getAddress().getNumber(),doctor.getAddress().getComplement())));
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
	
	@GetMapping("/{id}")
	public ResponseEntity<DataAnswerDoctor> getDoctor(@PathVariable Long id) {
		Doctor doctor = doctorRepository.getReferenceById(id);
		var dataDoctor = new DataAnswerDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getPhone(),
				doctor.getIdentification(), new DataAddress(doctor.getAddress().getStreet(),doctor.getAddress().getDistrict(),
						doctor.getAddress().getCity(),doctor.getAddress().getNumber(),doctor.getAddress().getComplement()));
		return ResponseEntity.ok(dataDoctor);
	}
}
