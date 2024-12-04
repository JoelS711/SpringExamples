package joels.medplus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import joels.medplus.api.domain.patient.DataRegisterPatient;
import joels.medplus.api.domain.patient.Patient;
import joels.medplus.api.domain.patient.PatientListData;
import joels.medplus.api.domain.patient.PatientRepository;
import joels.medplus.api.domain.patient.UpdatePatientData;

@RestController
@RequestMapping("patients")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;
	
	@PostMapping
    @Transactional
    public void register(@RequestBody @Valid DataRegisterPatient data) {
        patientRepository.save(new Patient(data));
    }

    @GetMapping
    public Page<PatientListData> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination) {
        return patientRepository.findAllByActiveTrue(pagination).map(PatientListData::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdatePatientData data) {
        var patient = patientRepository.getReferenceById(data.id());
        patient.updateInformation(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void eliminar(@PathVariable Long id) {
        var paciente = patientRepository.getReferenceById(id);
        paciente.delete();
    }
}
