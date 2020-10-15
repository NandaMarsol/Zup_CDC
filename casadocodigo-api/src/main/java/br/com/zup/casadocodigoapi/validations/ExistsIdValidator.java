package br.com.zup.casadocodigoapi.validations;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

public class ExistsIdValidator implements ConstraintValidator<ExistsId, Long> {

	private String domainAttribute;
	private Class<?> klass;

	@PersistenceContext
	private EntityManager manager;

	@Override
	public void initialize(ExistsId params) {
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	public boolean isValid(Long value, ConstraintValidatorContext context) {
		Query query = manager.createQuery("select 1 from " + klass.getName() + " where " + domainAttribute + "=:value");
		query.setParameter("value", value);

		List<?> list = query.getResultList();
		Assert.isTrue(list.size() <= 1, "Foi encontrado mais de um " + klass + " com o atributo " + domainAttribute
				+ " e com o valor = " + value);

		return !list.isEmpty();
	}
}

// a criação dessa classe é como uma receitinha de bolo
