package br.com.zup.casadocodigoapi.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.com.zup.casadocodigoapi.model.Autor;

public class NovoAutorRequest {

	@NotBlank private String nome; // campos não podem ser vazios
	@NotBlank @Email private String email; // formato de email válido
	@NotBlank @Size(max = 400) private String descricao; // máximo 400 caracteres
	
	// métodos
	public Autor toModel() {
		return new Autor(this.nome, this.email, this.descricao);
	}
	
	// getters
	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}
	
}

/*
 * classe criada para proteger a classe de domínio Autor
 * geralmente um "controller" acessa diretamente a classe de domínio, 
 * mas nesse caso você protege a classe de domínio e o controller acessa a classe que faz essa proteção.
 */ 
