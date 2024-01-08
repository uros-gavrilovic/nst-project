package nst.springboot.restexample01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class RestExample01Application {

	public static void main(String[] args) {
		SpringApplication.run(RestExample01Application.class, args);
	}

}
