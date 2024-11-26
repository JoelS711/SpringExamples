package joels.medplus.api.doctor;


public record DoctorListData(String name,String email,
		String identification,String speciality) {

	public DoctorListData(Doctor doctor) {
		this(doctor.getName(), doctor.getEmail(), doctor.getIdentification(), doctor.getSpeciality().toString());
	}

	
	
}
