package is.hi.hbv501.bokamarkadur.bokamarkadur;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class BokamarkadurApplication {

	public static void main(String[] args) {
		SpringApplication.run(BokamarkadurApplication.class, args);
	}

}
