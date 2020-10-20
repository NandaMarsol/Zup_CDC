package br.com.zup.casadocodigoapi.repository;

import org.springframework.data.repository.Repository;

import br.com.zup.casadocodigoapi.model.Cupom;

public interface CupomRepository extends Repository<Cupom, Long> {

	public Cupom getByCodigo(String codigo);
}
