package mzn.faisal.employeesmanagement.BusinessLayer.Service.security;

public interface TokenService {
    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
