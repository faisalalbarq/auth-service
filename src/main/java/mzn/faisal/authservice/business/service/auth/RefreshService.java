package mzn.faisal.authservice.business.service.auth;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.RefreshTokenRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final JwtService jwtService;

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String oldRefreshToken = request.refreshToken();

        if (!jwtService.validateToken(oldRefreshToken)) {
            throw new FrontendException("Invalid or expired refresh token", HttpStatus.UNAUTHORIZED);
        }

        String userId = jwtService.getUsernameFromToken(oldRefreshToken);

        String newAccessToken = jwtService.generateAccessToken(userId);

        return new LoginResponse(newAccessToken, oldRefreshToken);
    }
}
