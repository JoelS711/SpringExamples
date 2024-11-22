package joels.medplus.api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

	@PostMapping
	public void registerDoctor(@RequestBody String param) {
		System.out.println("The request is correct");
		System.out.println(param);
	}
}
