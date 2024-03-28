package org.ekl.backend.ws;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"org.ekl.backend"})
public class EklBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EklBackendApplication.class, args);
	}
}
