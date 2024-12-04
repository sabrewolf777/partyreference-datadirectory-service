package ec.com.dinersclub.dddmodules.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import jakarta.validation.ConstraintViolationException;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    public static final String INTERNAL_ERROR_CUSTOM_MESSAGE ="Error de lado del servidor";
    private static final String MENSAJE_ERROR_GENERAL="exception desde: GlobalExceptionHandler: {}";
	
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex) {
    	log.info("GlobalExceptionHandler: {}",ex);
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", System.currentTimeMillis());
        body.put("status", HttpStatus.BAD_REQUEST.value());

        Map<String, String> errors = new HashMap<>();
        ex.getConstraintViolations().forEach(violation -> {
            errors.put(
                violation.getPropertyPath().toString(),
                violation.getMessage()
            );
        });
        
        body.put("errors", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(NotFound.class)//404
    @ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "recurso no encontrado")
    public void everyException(NotFound e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(Unauthorized.class)//401
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED, reason = "recurso sin autorización")
    public void everyException(Unauthorized e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(Forbidden.class)//403
    @ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "recurso no encontrado")
    public void everyException(Forbidden e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    @ExceptionHandler(BadRequest.class)//400
    @ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "request mal implementado")
    public void everyException(BadRequest e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
    
    
    @ExceptionHandler(TimeoutException.class)//408
    @ResponseStatus(code = HttpStatus.REQUEST_TIMEOUT, reason = "excedio el tiempo de la petición")
    public void everyException(TimeoutException e) {
        log.info(MENSAJE_ERROR_GENERAL,e.getMessage());
    }
  
    @ExceptionHandler(Exception.class)//500
    public ResponseEntity<Object>  everyException(Exception e) {
        log.info(MENSAJE_ERROR_GENERAL,INTERNAL_ERROR_CUSTOM_MESSAGE +":::{"+e.getMessage()+"}");
        if(e.getMessage().indexOf("400") != -1) {
        	  Map<String, Object> body = new HashMap<>();
              body.put("timestamp", new Date());
              body.put("statusCode", HttpStatus.BAD_REQUEST.value());
              body.put("status", "FAILED");
              body.put("message", "Bad Request");
              return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }
        
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", new Date());
        body.put("statusCode", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("status", "FAILED");
        body.put("message", "Internal Server Error");
        
        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }
} 