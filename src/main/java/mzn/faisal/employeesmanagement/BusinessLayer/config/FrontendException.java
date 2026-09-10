package mzn.faisal.employeesmanagement.BusinessLayer.config;

public class FrontendException extends RuntimeException{
    public FrontendException(String key){
        super(key);
    }
}
