package br.com.zup.casadocodigoapi.validations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// classe para tratar todos os erros não tratados que aconteceram na camada da API
@RestControllerAdvice // dizendo ao Spring que a classe é uma HandlerAdvice
public class AutorValidacaoErroHandlerAdvice { // HandlerAdvice - lidar com exceções
	
	// criando método para tratar a exceção MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroPadronizado> handle(MethodArgumentNotValidException methodArgumentNotValidException) {
        Collection<String> mensagens = new ArrayList<>();
        BindingResult bindingResult = methodArgumentNotValidException.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        fieldErrors.forEach(fieldError -> {
            String message = String.format("Campo %s %s", fieldError.getField(), fieldError.getDefaultMessage());
            mensagens.add(message);
        });

        ErroPadronizado erroPadronizado = new ErroPadronizado(mensagens);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erroPadronizado);
    }
	

	
	

}
