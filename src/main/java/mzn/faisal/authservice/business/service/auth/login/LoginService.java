package mzn.faisal.authservice.business.service.auth.login;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.login.LoginRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.business.service.security.RefreshTokenService;
import mzn.faisal.authservice.data.db.entity.UserIdentity;
import mzn.faisal.authservice.data.db.entity.UserLogin;
import mzn.faisal.authservice.data.repository.UserIdentityRepository;
import mzn.faisal.authservice.data.repository.UserLoginRepository;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import mzn.faisal.authservice.utils.identity.IdentityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserLoginRepository userLoginRepository;
    private final UserIdentityRepository userIdentityRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;


    @Transactional
    public LoginResponse login(LoginRequest request){

        String formattedIdentity = IdentityUtils.validateAndFormat(request.identityValue());

        if(formattedIdentity.isBlank()){
            throw new FrontendException("invalidIdentityFormat");
        }

        UserIdentity userIdentity = userIdentityRepository.findByUserIdentityValue(formattedIdentity)
                .orElseThrow(() -> new FrontendException("invalidCredentials", HttpStatus.UNAUTHORIZED));

        UserLogin userLogin = userLoginRepository.findById(userIdentity.getUserLoginId())
                .orElseThrow(() -> new FrontendException("invalidCredentials", HttpStatus.UNAUTHORIZED));

        if (!passwordEncoder.matches(request.password(), userLogin.getPassword())){
            throw new FrontendException("invalidPassword", HttpStatus.UNAUTHORIZED);
        }

        String accessToken = jwtService.generateAccessToken(userLogin.getUserLoginId().toString());
        String refreshToken = jwtService.generateRefreshToken(userLogin.getUserLoginId().toString());
        return new LoginResponse(accessToken, refreshToken);
    }
}
