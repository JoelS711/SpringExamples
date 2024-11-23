package joels.medplus.api.doctor;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import joels.medplus.api.address.Address;
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
	private String identification;
	@Enumerated(EnumType.STRING)
	private Speciality speciality;
	@Embedded
	private Address address;
	
	public Doctor(DataDoctor dataDoctor) {
		this.name = dataDoctor.name();
		this.email = dataDoctor.email();
		this.identification = dataDoctor.identification();
		this.speciality = dataDoctor.speciality();
		this.address = new Address(dataDoctor.address());
	}
}
