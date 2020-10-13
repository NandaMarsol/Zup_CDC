package br.com.zup.casadocodigoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false) // comportamento default de abrir transações desabilitado
public class CasadocodigoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CasadocodigoApiApplication.class, args);
	}

}
