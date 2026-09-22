package mzn.faisal.authservice.business.service.security;

public interface TokenService {
    String generateToken(String username, Long expirationTime);
    boolean validateToken(String token);
    String getUsernameFromToken(String token);
    String extractTokenFromHeader(String authHeader);
}
