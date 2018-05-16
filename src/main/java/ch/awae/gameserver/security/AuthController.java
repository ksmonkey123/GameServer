package ch.awae.gameserver.security;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ch.awae.gameserver.core.UserService;
import lombok.Data;

@RestController
public class AuthController {

	@Autowired
	private PasswordEncoder crypto;

	@Autowired
	private AuthService authService;

	@Autowired
	private TokenService tokenService;

	@Autowired
	private UserService userService;

	@PostMapping("/auth/login")
	public TokenResponse doLogin(@Valid @RequestBody LoginRequest request) {
		authService.login(request.username, request.password);
		return new TokenResponse(tokenService.createToken());
	}

	@PostMapping("/auth/register")
	public TokenResponse doRegister(@Valid @RequestBody SignupRequest request) {
		userService.createUser(request.username, crypto.encode(request.password));
		authService.login(request.username, request.password);
		return new TokenResponse(tokenService.createToken());
	}

	@Data
	public static class SignupRequest {
		@NotBlank
		@Size(min = 3, max = 15)
		private String username;
		@NotBlank
		@Size(min = 6, max = 20)
		private String password;
	}

	@Data
	public static class LoginRequest {
		@NotBlank
		@Size(min = 3, max = 15)
		private String username;
		@NotBlank
		@Size(min = 6, max = 20)
		private String password;
	}

	@Data
	public static class TokenResponse {
		private final String token;
	}

}
