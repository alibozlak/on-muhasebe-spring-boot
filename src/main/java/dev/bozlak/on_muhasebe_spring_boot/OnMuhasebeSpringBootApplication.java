package dev.bozlak.on_muhasebe_spring_boot;

import jakarta.annotation.PostConstruct;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.TimeZone;

@SpringBootApplication
public class OnMuhasebeSpringBootApplication {

	@PostConstruct
	public void setDefaultTimeZonePlus3(){
		TimeZone.setDefault(TimeZone.getTimeZone("GMT+3"));
	}

	@Bean
	public PasswordEncoder bCyrptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(OnMuhasebeSpringBootApplication.class, args);
	}

}
