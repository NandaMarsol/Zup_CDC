package br.com.zup.casadocodigoapi.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.casadocodigoapi.model.Categoria;

@Repository
public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	Optional<Categoria> findByNome(String nome);

}
