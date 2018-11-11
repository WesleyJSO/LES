package br.com.les.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {

	public static void main( String[] args ) {
		System.setProperty("spring.devtools.restart.enabled", "false");
		SpringApplication.run( BackendApplication.class, args );
	}
	
	// Make password encrypt an autowirable Been for all application
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
