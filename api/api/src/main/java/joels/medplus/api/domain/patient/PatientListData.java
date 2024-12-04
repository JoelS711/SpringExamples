package joels.medplus.api.domain.patient;

public record PatientListData(Long id, String name, String email, String identification) {

	public PatientListData(Patient patient) {
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getIdentification());
    }
}
