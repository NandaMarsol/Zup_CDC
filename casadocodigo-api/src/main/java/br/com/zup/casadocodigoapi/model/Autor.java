package br.com.zup.casadocodigoapi.model;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity // indica que o autor é uma entidade armazenável no banco de dados
public class Autor {
	
	@Id // o atributo é uma PK no banco de dados
	@GeneratedValue(strategy = GenerationType.IDENTITY) // valor da chave primária será gerada pela coluna auto increment
	private Long id;
	@NotBlank private String nome;
	@NotBlank @Email private String email;
	@NotBlank @Size(max = 400) private String descricao;
	@NotNull private LocalDateTime instanteDaCriacao = LocalDateTime.now(); // não pode ser nulo

	@Deprecated 
	public Autor() {
	}
	
	// construtor
	public Autor(@NotBlank String nome, @NotBlank @Email String email, 
			@NotBlank @Size(max = 400) String descricao) {
		this.nome = nome;
		this.email = email;
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		return "Autor [id=" + id + ", nome=" + nome + ", email=" + email + ", descricao=" + descricao
				+ ", instanteDaCriacao=" + instanteDaCriacao + "]";
	}
	

}
