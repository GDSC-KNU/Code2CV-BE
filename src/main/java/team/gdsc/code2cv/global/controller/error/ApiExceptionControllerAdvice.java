package team.gdsc.code2cv.global.controller.error;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import team.gdsc.code2cv.core.exception.ExternalServerCommunicationException;
import team.gdsc.code2cv.core.exception.NotAuthorizationException;
import team.gdsc.code2cv.core.exception.TokenExpiredException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionControllerAdvice {
	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse illegalArgumentExceptionHandler(IllegalArgumentException ex) {
		log.debug("IllegalArgumentException : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("IllegalArgumentException")
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorResponse methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		BindingResult bindingResult = ex.getBindingResult();

		List<FieldError> fieldErrors = bindingResult.getFieldErrors();

		return ErrorResponse.builder()
			.debugMessage("MethodArgumentNotValidException")
			.code(fieldErrors.get(0).getField())
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorResponse resourceNotFoundExceptionHandler(NoSuchElementException ex) {
		log.info("ResourceNotFoundException : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("ResourceNotFoundException")
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse authenticationExceptionHandler(NotAuthorizationException ex) {
		log.error("AuthenticationException : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("NotAuthorizationException")
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ErrorResponse tokenExpiredExceptionHandler(TokenExpiredException ex) {
		log.error("TokenExpiredException : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("JWT")
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse exceptionHandler(ExternalServerCommunicationException ex) {
		log.error("Exception : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("ExternalServerCommunicationException")
			.build();
	}

	@ExceptionHandler
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ErrorResponse runtimeExceptionHandler(RuntimeException ex) {
		log.error("RuntimeException : {}", ex.getMessage());
		return ErrorResponse.builder()
			.debugMessage(ex.getMessage())
			.code("RuntimeException")
			.build();
	}
}
