package mil.navy.spawar.udb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class UdbApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(UdbApplication.class, args);
	}

}
