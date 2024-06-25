package souza.marlon.moneymanager.handler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import souza.marlon.moneymanager.exception.BadRequestException;
import souza.marlon.moneymanager.exception.BadRequestExceptionDetails;
import souza.marlon.moneymanager.exception.ExceptionDetails;
import souza.marlon.moneymanager.exception.ValidationExceptionDetails;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException) {
        var badRequestExceptionDetails = new BadRequestExceptionDetails();
        badRequestExceptionDetails.setTime(LocalDateTime.now());
        badRequestExceptionDetails.setStatus(HttpStatus.BAD_REQUEST.value());
        badRequestExceptionDetails.setTitle("Bad Request Exception, Check the Documentation");
        badRequestExceptionDetails.setDetails(badRequestException.getMessage());
        badRequestExceptionDetails.setDeveloperMessage(badRequestExceptionDetails.getClass().getName());
        return new ResponseEntity<>(badRequestExceptionDetails, HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        var fieldErrors = ex.getBindingResult().getFieldErrors();
        var fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(","));
        var fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(","));
        return new ResponseEntity<>(new ValidationExceptionDetails(
                "Bad Request Exception, Invalid Fields",
                HttpStatus.BAD_REQUEST.value(),
                "Check the fields error",
                ex.getClass().getName(),
                LocalDateTime.now(),
                fields,
                fieldsMessage
        ), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        var exceptionDetails = new ExceptionDetails();
        exceptionDetails.setTime(LocalDateTime.now());
        exceptionDetails.setStatus(statusCode.value());
        exceptionDetails.setTitle(ex.getMessage());
        exceptionDetails.setDetails("Check the fields error");
        exceptionDetails.setDeveloperMessage(ex.getClass().getName());
        return new ResponseEntity<>(exceptionDetails, headers, statusCode);
    }
}
