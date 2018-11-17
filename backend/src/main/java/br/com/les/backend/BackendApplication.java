package br.com.les.backend;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class BackendApplication {

    @PostConstruct
    public void init(){
    	Calendar calendar = new GregorianCalendar();
    	TimeZone systemTimeZone = calendar.getTimeZone();
    	TimeZone.setDefault(systemTimeZone);
    	// Simple Log
        System.out.println("\n###############################################################################################\n");
        System.out.println(" Spring boot application running in America/SAO_PAULO timezone: " + new Date());
        System.out.println(" Spring boot application running in America/SAO_PAULO timezone: " + LocalDateTime.now());
        System.out.println("\n###############################################################################################\n");
    }
	
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
