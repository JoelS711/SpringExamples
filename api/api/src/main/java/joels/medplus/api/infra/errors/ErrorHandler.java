package joels.medplus.api.infra.errors;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;
import joels.medplus.api.domain.ValidaException;

@RestControllerAdvice
public class ErrorHandler {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity tryError404() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity tryError404(MethodArgumentNotValidException e) {
		var errors = e.getFieldErrors().stream().map(DataErrorValidation::new).toList();
		return ResponseEntity.badRequest().body(errors);
	}
	
	@ExceptionHandler(ValidaException.class)
	public ResponseEntity tryErrorValidation(ValidaException e) {
		return ResponseEntity.badRequest().body(e.getMessage());
	}
	
	private record DataErrorValidation(String field, String error) {
		public DataErrorValidation(FieldError error) {
			this(error.getField(), error.getDefaultMessage());
		}
	}
}
