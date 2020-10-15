package br.com.zup.casadocodigoapi.validations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// criando uma anotação para validar o Id no banco de dados

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { ExistsIdValidator.class })
public @interface ExistsId {

	String message() default "Não está cadastrado!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	String fieldName();

	Class<?> domainClass();

}

// a criação dessa classe é como uma receitinha de bolo
