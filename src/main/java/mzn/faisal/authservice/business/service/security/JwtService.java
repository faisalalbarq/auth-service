package mzn.faisal.authservice.business.service.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService implements TokenService {

    private final Long accessExpiration;
    private final Long refreshExpiration;
    private final SecretKey key;

    public JwtService(
            @Value("${jwt.key}") String secret,
            @Value("${jwt.access-token-expiration}") Long accessExpiration,
            @Value("${jwt.refresh-token-expiration}") Long refreshExpiration
    ){
        this.accessExpiration = accessExpiration;
        this.refreshExpiration = refreshExpiration;
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public Claims getAllClaimsFromToken(String token){
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private Boolean isTokenExpired(String token) {
        try {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    @Override
    public boolean validateToken(String token) {
        try {
            return !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }


    public String generateAccessToken(String username){
        return generateToken(username, accessExpiration);
    }

    public String generateRefreshToken(String username){
        return generateToken(username, refreshExpiration);
    }



    @Override
    public String generateToken(String username, Long expirationTime){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .subject(username)
                .issuedAt(now)
                .expiration(expirationDate)
                .signWith(key)
                .compact();
    }

    public Long getRemainingExpirationMs(String token){
        Date expiration  = getExpirationDateFromToken(token);
        long remainingMs = expiration.getTime() - System.currentTimeMillis();
        return remainingMs > 0 ? remainingMs : 0L;
    }

    public String extractTokenFromHeader(String header) {
        if (header != null && header.startsWith("Bearer ")) {
            return header.substring(7);
        }
        return null;
    }
}
