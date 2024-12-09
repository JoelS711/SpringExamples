package joels.medplus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import joels.medplus.api.domain.consult.ConsultationReservation;
import joels.medplus.api.domain.consult.DataDetailConsult;
import joels.medplus.api.domain.consult.DataReserveConsult;

@RestController
@RequestMapping("consults")
public class ConsultController {
	
	@Autowired
	private ConsultationReservation reservation;

	@PostMapping
	@Transactional
	public ResponseEntity reserve(@RequestBody @Valid DataReserveConsult data) {
		reservation.reservation(data);
		return ResponseEntity.ok(new DataDetailConsult(null, null, null, null));
	}
}
