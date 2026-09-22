package mzn.faisal.authservice.business.service.auth.logout;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.common.RedisService;
import mzn.faisal.authservice.business.service.security.JwtService;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class LogoutService {

    private final RedisService redisService;
    private final JwtService jwtService;

    public void logout(String accessTokenHeader, String refreshToken) {
        String accessToken = jwtService.extractTokenFromHeader(accessTokenHeader);

        if (accessToken != null) {
            Long remainingMs = jwtService.getRemainingExpirationMs(accessToken);

            if (remainingMs > 0) {
                redisService.set("blacklist:" + accessToken, "logout", Duration.ofMillis(remainingMs));
            }
        }

        if (refreshToken != null && !refreshToken.isBlank()) {
            Long remainingMs = jwtService.getRemainingExpirationMs(refreshToken);
            if (remainingMs > 0) {
                redisService.set("blacklist:" + refreshToken, "logout", Duration.ofMillis(remainingMs));
            }
        }
    }

}
