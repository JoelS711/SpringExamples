package joels.medplus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import joels.medplus.api.domain.user.DataAuthUser;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping
	public ResponseEntity authUser(DataAuthUser dataAuthUser) {
		Authentication token = new UsernamePasswordAuthenticationToken(dataAuthUser.user(),dataAuthUser.pass());
		authenticationManager.authenticate(token);
		return ResponseEntity.ok().build();
	}

}
