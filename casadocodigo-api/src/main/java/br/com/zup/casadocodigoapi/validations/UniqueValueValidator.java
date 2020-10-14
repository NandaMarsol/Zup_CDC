package br.com.zup.casadocodigoapi.validations;
// classe que representa o nosso validador

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.util.Assert;

// implementando a interface ConstraintValidator do Bean Validation
public class UniqueValueValidator implements ConstraintValidator<UniqueValue, String>{
	
	private String domainAttribute;
	private Class<?> klass;
	
	@PersistenceContext
	private EntityManager manager;
	
	public void initialize(UniqueValue params){
		domainAttribute = params.fieldName();
		klass = params.domainClass();
	}

	// implementando método isValid
	public boolean isValid(String value, ConstraintValidatorContext context){
			Query query = manager.createQuery("select 1 from " +klass.getName() + " where " +domainAttribute+ "=:value");
			query.setParameter("value", value);
			List<?> list = query.getResultList();
			Assert.state(list.size() <=1, "Foi encontrado mais de um " +klass+ " com o atributo " +domainAttribute+ " = " +value);
			
			return list.isEmpty();
		
		// return StringUtils.isBlank(valor);
	}
}
