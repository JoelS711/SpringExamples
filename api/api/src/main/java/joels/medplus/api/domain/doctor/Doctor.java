package joels.medplus.api.domain.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import joels.medplus.api.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="doctors")
@Entity(name="Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String identification;
	private boolean active;
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	@Embedded
	private Address address;
	
	
	
	public Doctor(DataRegisterDoctor dataDoctor) {
		this.active = true;
		this.name = dataDoctor.name();
		this.email = dataDoctor.email();
		this.phone = dataDoctor.phone();
		this.identification = dataDoctor.identification();
		this.speciality = dataDoctor.speciality();
		this.address = new Address(dataDoctor.address());
	}



	public void updateDoctorData(UpdateDoctorData updateDoctorData) {
		if(updateDoctorData.name() != null) {
			this.name = updateDoctorData.name();			
		}
		if(updateDoctorData.identification() != null) {
			this.identification = updateDoctorData.identification();			
		}
		if(updateDoctorData.address() != null) {
			this.address = address.updateAddressData(updateDoctorData.address());			
		}
		
	}



	public void inactiveDoctor() {
		this.active=false;
		
	}

}
