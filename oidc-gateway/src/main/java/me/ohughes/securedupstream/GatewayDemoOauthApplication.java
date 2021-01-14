package me.ohughes.securedupstream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.filter.factory.TokenRelayGatewayFilterFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtValidators;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@SpringBootApplication
public class GatewayDemoOauthApplication {

		@Autowired
		TokenRelayGatewayFilterFactory tokenRelayGatewayFilterFactory;

	@Bean
	public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
		http.csrf().disable()
			.authorizeExchange()
			.anyExchange()
			.authenticated()
			.and().oauth2Login()
			.and().oauth2ResourceServer()
				.jwt()
				.jwkSetUri("https://api.github.com/app/1363771");
		return http.build();
	}

	public static void main(String[] args) {
		SpringApplication.run(GatewayDemoOauthApplication.class, args);
	}

}
