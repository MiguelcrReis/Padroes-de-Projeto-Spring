package one.digitalinnovation.gof;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * Projeto Spring Boot criado no Spring Initializr, utilizando os seguintes módulos?
 * # Spring Data JPA
 * # Spring Web
 * # H2 Database
 *  # OpenFeign
 *
 * @author MiguelcrReis
 */

@EnableFeignClients //habilita o Client do Feign no projeto
@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

}
