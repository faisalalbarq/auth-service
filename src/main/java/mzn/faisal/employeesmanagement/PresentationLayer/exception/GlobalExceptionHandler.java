package mzn.faisal.employeesmanagement.PresentationLayer.exception;

import mzn.faisal.employeesmanagement.BusinessLayer.config.FrontendException;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleFrontendException(FrontendException ex) {

        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(ex.getMessage());
        response.setCode(ex.getStatus().value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        ExceptionResponse response = new ExceptionResponse();

        var fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "validationFailed";

        response.setMessage(errorMessage);
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
