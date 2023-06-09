package coj.and.CaloriesCalculator.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleApiExceptions(MethodArgumentNotValidException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<Object> handleApiExceptions(ConstraintViolationException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.BAD_REQUEST, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleApiExceptions(NotFoundException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.NOT_FOUND, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = AlreadyExistsException.class)
    public ResponseEntity<Object> handleApiExceptions(AlreadyExistsException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = UnauthorizedException.class)
    public ResponseEntity<Object> handleApiExceptions(UnauthorizedException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.UNAUTHORIZED, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }

    @ExceptionHandler(value = ConflictException.class)
    public ResponseEntity<Object> handleApiExceptions(ConflictException e) {
        ApiException apiException = new ApiException(e.getMessage(), HttpStatus.CONFLICT, ZonedDateTime.now());
        return new ResponseEntity<>(apiException, apiException.httpStatus());
    }
}
