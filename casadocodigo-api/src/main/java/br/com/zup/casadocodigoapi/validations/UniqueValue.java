package br.com.zup.casadocodigoapi.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// criando minha própria anotação

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = {UniqueValueValidator.class}) // informando que a anotação é uma Constraint
public @interface UniqueValue {
	
	String message() default "Já está cadastrado!";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
	
	String fieldName();
	
	Class<?> domainClass();

}
