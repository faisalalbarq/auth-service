package mzn.faisal.employeesmanagement.presentation.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class FrontendException extends RuntimeException{

    private final HttpStatus status;

    public FrontendException(String key) {
        super(key);
        this.status = HttpStatus.BAD_REQUEST;
    }

    public FrontendException(String key, HttpStatus status){
        super(key);
        this.status = status;
    }

}
