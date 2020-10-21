package br.com.zup.casadocodigoapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.zup.casadocodigoapi.model.Cupom;

@Repository
public interface CupomRepository extends JpaRepository<Cupom, Long> {

	public Cupom getByCodigo(String codigo);

}
