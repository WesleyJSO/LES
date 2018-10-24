package br.com.les.backend.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.headers().frameOptions().disable()
		.and()
		.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
		/*.antMatchers(HttpMethod.POST, "/login").permitAll()
		.antMatchers(HttpMethod.POST, "/h2/**").permitAll()
		.antMatchers(HttpMethod.GET, "/h2/**").permitAll()*/
		.antMatchers("/**").permitAll()
		//.anyRequest().authenticated()
		.and()
		
		// filtra requisições de login
		.addFilterBefore(new JWTLoginFilter("/login", authenticationManager()), UsernamePasswordAuthenticationFilter.class)
		
		// filtra outras requisições para verificar a presença do JWT no header
		.addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// auth.userDetailsService( (UserDetailsService) new User() );
		
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		// cria uma conta default temporáriamente
				auth.inMemoryAuthentication()
					.withUser("zeller@zeller.com")
					.password(encoder.encode("1234"))
					.roles("USER")
					.and()
					.withUser("wesley@wesley.com")
					.password(encoder.encode("1234"))
					.roles("ADMIN");
	}
}
