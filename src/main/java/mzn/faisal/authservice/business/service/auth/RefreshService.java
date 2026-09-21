package mzn.faisal.authservice.business.service.auth;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.RefreshTokenRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.business.service.security.RefreshTokenService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RefreshService {

    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;

    public LoginResponse refreshToken(RefreshTokenRequest request) {
        String userId = refreshTokenService.validateRefreshToken(request.refreshToken());

        String newAccessToken = jwtService.generateToken(userId);

        return new LoginResponse(newAccessToken, request.refreshToken());
    }
}
