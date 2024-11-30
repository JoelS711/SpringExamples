package joels.medplus.api.domain.doctor;


public record DoctorListData(Long id, String name,String email,
		String identification,String speciality) {

	public DoctorListData(Doctor doctor) {
		this(doctor.getId() ,doctor.getName(), doctor.getEmail(), doctor.getIdentification(), doctor.getSpeciality().toString());
	}

	
	
}
