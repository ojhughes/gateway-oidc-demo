package me.ohughes.securedupstream;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ollie Hughes
 */

@SpringBootApplication
@RestController
public class SecuredUpstreamApplication {

	@GetMapping("/whoami")
	public String whomami(@AuthenticationPrincipal Jwt jwt) {
		return jwt.getTokenValue();
	}

	public static void main(String[] args) {
		SpringApplication.run(SecuredUpstreamApplication.class, args);
	}

}
