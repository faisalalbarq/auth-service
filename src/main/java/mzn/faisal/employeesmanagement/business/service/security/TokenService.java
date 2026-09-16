package mzn.faisal.employeesmanagement.business.service.security;

public interface TokenService {
    String generateToken(String username);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
}
