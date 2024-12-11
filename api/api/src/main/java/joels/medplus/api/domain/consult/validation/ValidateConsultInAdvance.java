package joels.medplus.api.domain.consult.validation;

import java.time.Duration;
import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.DataReserveConsult;

@Component
public class ValidateConsultInAdvance implements ValidateOfConsult{

	public void validate(DataReserveConsult data) {
		var dateConsult = data.date();
		var now = LocalDateTime.now();
		var differenceInMinutes = Duration.between(now, dateConsult).toMinutes();
		if(differenceInMinutes < 30) {
			throw new ValidaException("The selected time must be 30 minutes in advance");
		}
	}
}
