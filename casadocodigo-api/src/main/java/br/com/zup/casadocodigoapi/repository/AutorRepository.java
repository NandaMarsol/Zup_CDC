package br.com.zup.casadocodigoapi.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.casadocodigoapi.model.Autor;

@Repository 
public interface AutorRepository extends CrudRepository<Autor, Long> {

}
