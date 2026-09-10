package mzn.faisal.employeesmanagement.PresentationLayer.exception;

import mzn.faisal.employeesmanagement.BusinessLayer.config.FrontendException;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleFrontendException(FrontendException ex) {
        ErrorResponse response = new ErrorResponse();

        response.setMessage(ex.getMessage());
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
