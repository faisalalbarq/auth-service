package mzn.faisal.authservice.business.service.auth;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.RefreshTokenRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.service.common.RedisService;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final JwtService jwtService;
    private final RedisService redisService;

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String refreshToken = request.refreshToken();

        if (redisService.hasKey("blacklist:" + refreshToken)) {
            throw new FrontendException("invalidRefreshToken", HttpStatus.UNAUTHORIZED);
        }

        if (!jwtService.validateToken(refreshToken)) {
            throw new FrontendException("Invalid or expired refresh token", HttpStatus.UNAUTHORIZED);
        }

        String userId = jwtService.getUsernameFromToken(refreshToken);

        String newAccessToken = jwtService.generateAccessToken(userId);

        return new LoginResponse(newAccessToken, refreshToken);
    }
}
