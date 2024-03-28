package org.ekl.backend.ws.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {UsernameOrPasswordInvalidException.class})
    public ResponseEntity<?> handleUsernameOrPasswordInvalidException(Exception exception, WebRequest request){
        log.info("Username or password invalid", exception);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @ExceptionHandler(value={IllegalArgumentException.class})
    public ResponseEntity<?> handleBadRequest(Exception exception, WebRequest request){
        log.info("bad request recieved.", exception);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @ExceptionHandler(value = {UserAlreadyExistException.class})
    public ResponseEntity<?> handleDatabaseException(Exception ex, WebRequest request) throws Exception {
        if(ex instanceof UserAlreadyExistException){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }else {
            throw ex;
        }
    }

    @ExceptionHandler(value = {AccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedExcpetion(Exception ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleUnknownException(Exception exception, WebRequest request){
        log.error("Unknown exception occured.", exception);
        return ResponseEntity.internalServerError().build();
    }
}
