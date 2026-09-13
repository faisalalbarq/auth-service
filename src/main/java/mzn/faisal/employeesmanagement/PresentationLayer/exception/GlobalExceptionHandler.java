package mzn.faisal.employeesmanagement.PresentationLayer.exception;

import mzn.faisal.employeesmanagement.BusinessLayer.config.FrontendException;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.common.ExceptionResponse;
import org.springframework.http.ResponseEntity;
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
}
