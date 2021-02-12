package br.com.alura.forum.config.validacao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import br.com.alura.forum.config.validacao.dto.FormErrorDto;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
@RestControllerAdvice
public class ValidationErrorHandler {
	@Autowired
	private MessageSource ms;
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public List<FormErrorDto> handler(MethodArgumentNotValidException e) {
		List<FormErrorDto> el = new ArrayList<>();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
				
		fieldErrors.forEach(fe -> {
			String mensagem = ms.getMessage(fe, LocaleContextHolder.getLocale());
			
			FormErrorDto eDto = new FormErrorDto(fe.getField(), mensagem);
			
			el.add(eDto);
		});
		
		return el;
	}
}
