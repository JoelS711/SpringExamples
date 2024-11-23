package joels.medplus.api.doctor;

import joels.medplus.api.address.DataAddress;

public record DataDoctor(String name,
		String email,
		String identification,
		Speciality speciality,
		DataAddress	address) {

}
