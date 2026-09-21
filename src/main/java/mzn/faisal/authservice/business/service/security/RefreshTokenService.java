package mzn.faisal.authservice.business.service.security;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.common.RedisService;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenService {

    private final RedisService redisService;

    @Value("${jwt.refresh-token-expiration}")
    private Long expiration;

    public String generateRefreshToken(String userId){
        String   refreshToken = UUID.randomUUID().toString();
        Duration timeout = Duration.ofMillis(expiration);
        String   key = "refresh_token:" + refreshToken;

        redisService.set(key, userId, timeout);
        return refreshToken;
    }

    public String validateRefreshToken(String refreshToken){
        String key = "refresh_token:" + refreshToken;
        Object userId = redisService.get(key);

        if (userId == null) {
            throw new FrontendException("Invalid or expired refresh token", HttpStatus.UNAUTHORIZED);
        }

        return userId.toString();
    }

    public void deleteRefreshToken(String refreshToken){
        String key = "refresh_token:" + refreshToken;
        redisService.delete(key);
    }
}
