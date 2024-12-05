package joels.medplus.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import joels.medplus.api.domain.consult.DataReserveConsult;

@RestController
@RequestMapping("consults")
public class ConsultController {

	@PostMapping
	@Transactional
	public ResponseEntity reserve(@RequestBody @Valid DataReserveConsult data) {
		System.out.println(data);
		return ResponseEntity.ok(new DataDetailConsult(null, null, null, null));
	}
}
