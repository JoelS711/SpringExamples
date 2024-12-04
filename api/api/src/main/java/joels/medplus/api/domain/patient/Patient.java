package joels.medplus.api.domain.patient;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import joels.medplus.api.domain.address.Address;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name="patients")
@Entity(name="Patient")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Patient {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String phone;
    private String identification;
    @Embedded
    private Address address;
    private Boolean active;
    
    public Patient(DataRegisterPatient data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.phone = data.phone();
        this.identification = data.identification();
        this.address = new Address(data.address());
    }

    public void updateInformation(UpdatePatientData data) {
        if (data.name() != null) {
            this.name = data.name();
        }
        if (data.phone() != null) {
            this.phone = data.phone();
        }
        if (data.address() != null) {
            this.address.updateAddressData(data.address());
        }
    }

    public void delete() {
        this.active = false;
    }
}
