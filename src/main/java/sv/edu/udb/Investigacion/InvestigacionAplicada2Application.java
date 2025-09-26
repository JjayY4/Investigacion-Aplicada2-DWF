package sv.edu.udb.Investigacion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class InvestigacionAplicada2Application {

	public static void main(String[] args) {
		SpringApplication.run(InvestigacionAplicada2Application.class, args);
	}

}
