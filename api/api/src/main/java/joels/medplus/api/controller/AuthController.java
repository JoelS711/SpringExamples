package joels.medplus.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import joels.medplus.api.domain.user.DataAuthUser;
import joels.medplus.api.domain.user.User;
import joels.medplus.api.infra.security.DataJWTToken;
import joels.medplus.api.infra.security.TokenService;

@RestController
@RequestMapping("/login")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity authUser(@RequestBody @Valid DataAuthUser dataAuthUser) {
		Authentication authToken = new UsernamePasswordAuthenticationToken(dataAuthUser.user(),dataAuthUser.pass());
		var authenticatedUser = authenticationManager.authenticate(authToken);
		var JWTtoken = tokenService.generateToken((User) authenticatedUser.getPrincipal());
		return ResponseEntity.ok(new DataJWTToken(JWTtoken));
	}

}
