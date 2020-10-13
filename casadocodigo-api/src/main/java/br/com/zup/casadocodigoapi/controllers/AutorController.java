package br.com.zup.casadocodigoapi.controllers;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.casadocodigoapi.model.Autor;
import br.com.zup.casadocodigoapi.repository.AutorRepository;
import br.com.zup.casadocodigoapi.request.NovoAutorRequest;

@RestController // transforma a classe em Rest Controller
public class AutorController {
	
	@Autowired // define a classe como pertencente a camada de persistência
	private AutorRepository autorRepository;
	
	// criando autor 
	@PostMapping(value = "/api/autor") // faz o endpoint ser acessível via método POST e define a URI /autor
	@Transactional // transação no banco de dados
	public void criarAutor(@Valid @RequestBody NovoAutorRequest request){
		Autor autor = request.toModel();
		autorRepository.save(autor); // salvando no banco de dados
	}

}




/*
 * no método criarAutor, dentro do argumento será o corpo da resposta em formato JSON, 
 * a anotação @RequestBody faz isso e @Valid serve para indicar que o objeto será validado, 
 * incluí a classe NovoAutorRequest para o Controller acessar essa classe e assim proteger 
 * a classe de domínio Autor.
 */