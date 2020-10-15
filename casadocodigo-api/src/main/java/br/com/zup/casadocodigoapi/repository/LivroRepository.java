package br.com.zup.casadocodigoapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.casadocodigoapi.model.Livro;

@Repository
public interface LivroRepository extends CrudRepository<Livro, Long> {
	

}

// ainda não estou usando essa classe, mas resolvi já deixar criada
