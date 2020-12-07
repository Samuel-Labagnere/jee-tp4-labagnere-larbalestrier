package lp.dawin.covidpcrcenters;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CovidPcrCentersApplication {

	public static void main(String[] args) {
		SpringApplication.run(CovidPcrCentersApplication.class, args);
	}

}
