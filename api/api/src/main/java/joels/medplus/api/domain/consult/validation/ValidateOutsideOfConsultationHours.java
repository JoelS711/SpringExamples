package joels.medplus.api.domain.consult.validation;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import joels.medplus.api.domain.ValidaException;
import joels.medplus.api.domain.consult.DataReserveConsult;

@Component
public class ValidateOutsideOfConsultationHours implements ValidateOfConsult{

	public void validate(DataReserveConsult data) {
		var consultationDate = data.date();
		var sunday = consultationDate.getDayOfWeek().equals(DayOfWeek.SUNDAY);
		var hoursBeforeOpening = consultationDate.getHour() < 7;
		var afterClosingHours = consultationDate.getHour() > 18;
		if(sunday || hoursBeforeOpening || afterClosingHours) {
			throw new ValidaException("Selected time outside of service hours");
		}
	}
}
