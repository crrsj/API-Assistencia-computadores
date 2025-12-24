package br.com.computador;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "API - Assistência Técnica Para PCs e Notebooks",
				version = "1.0",
				description = "API para gerenciamento de serviços de PCs e Notebooks",
				contact = @Contact(name = "Carlos Roberto", email = "crrsj1@gmail.com")
		)
)
public class ComputadoresApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComputadoresApplication.class, args);
	}

}
