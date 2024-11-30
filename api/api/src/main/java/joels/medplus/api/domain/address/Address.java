package joels.medplus.api.domain.address;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

	private String street;
	private String district;
	private String city;
	private String number;
	private String complement;

	
	public Address(DataAddress dataAddress) {
		this.street = dataAddress.street();
		this.district = dataAddress.district();
		this.city = dataAddress.city();
		this.number = dataAddress.number();
		this.complement = dataAddress.complement();
	}


	public Address updateAddressData(DataAddress dataAddress) {
		this.street = dataAddress.street();
		this.district = dataAddress.district();
		this.city = dataAddress.city();
		this.number = dataAddress.number();
		this.complement = dataAddress.complement();
		return this;
	}
}
