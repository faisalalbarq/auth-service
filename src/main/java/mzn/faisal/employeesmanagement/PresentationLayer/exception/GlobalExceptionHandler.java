package mzn.faisal.employeesmanagement.PresentationLayer.exception;

import jakarta.servlet.http.HttpServletRequest;
import mzn.faisal.employeesmanagement.BusinessLayer.Service.common.TranslationService;
import mzn.faisal.employeesmanagement.BusinessLayer.config.FrontendException;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.common.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import lombok.RequiredArgsConstructor;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final TranslationService translationService;

    @ExceptionHandler
    public ResponseEntity<ExceptionResponse> handleFrontendException(
            FrontendException ex,
            HttpServletRequest request
    ) {
        String lang = getLanguageFromRequest(request);

        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(translationService.translate(ex.getMessage(), lang));
        response.setCode(ex.getStatus().value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ) {
        String lang = getLanguageFromRequest(request);

        var fieldError = ex.getBindingResult().getFieldError();
        String errorMessage = (fieldError != null) ? fieldError.getDefaultMessage() : "validationFailed";

        ExceptionResponse response = new ExceptionResponse();
        response.setMessage(translationService.translate(errorMessage, lang));
        response.setCode(HttpStatus.BAD_REQUEST.value());
        response.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private String getLanguageFromRequest(HttpServletRequest request) {
        String lang = request.getHeader("Accept-Language");
        return (lang != null && !lang.isBlank()) ? lang : "ar";
    }
}
