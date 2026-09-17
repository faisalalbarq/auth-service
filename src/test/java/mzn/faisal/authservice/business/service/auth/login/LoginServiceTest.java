package mzn.faisal.authservice.business.service.auth.login;

import mzn.faisal.authservice.business.dto.auth.login.LoginRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.data.db.entity.UserIdentity;
import mzn.faisal.authservice.data.db.entity.UserLogin;
import mzn.faisal.authservice.data.repository.UserIdentityRepository;
import mzn.faisal.authservice.data.repository.UserLoginRepository;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import mzn.faisal.authservice.utils.identity.IdentityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginServiceTest {

    @Mock
    private UserLoginRepository userLoginRepository;

    @Mock
    private UserIdentityRepository userIdentityRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private LoginService loginService;

    private LoginRequest validRequest;
    private UserIdentity userIdentity;
    private UserLogin userLogin;
    private UUID userLoginId;

    @BeforeEach
    void setUp() {
        userLoginId = UUID.randomUUID();
        validRequest = new LoginRequest("test@example.com", "Password123!");

        userIdentity = new UserIdentity();
        userIdentity.setUserIdentityValue("test@example.com");
        userIdentity.setUserLoginId(userLoginId);

        userLogin = new UserLogin();
        userLogin.setUserLoginId(userLoginId);
        userLogin.setPassword("hashedPassword");
    }

    @Test
    void login_Success() {
        try (MockedStatic<IdentityUtils> identityUtilsMock = mockStatic(IdentityUtils.class)) {
            identityUtilsMock.when(() -> IdentityUtils.validateAndFormat("test@example.com"))
                    .thenReturn("test@example.com");

            when(userIdentityRepository.findByUserIdentityValue("test@example.com"))
                    .thenReturn(Optional.of(userIdentity));
            when(userLoginRepository.findById(userLoginId))
                    .thenReturn(Optional.of(userLogin));
            when(passwordEncoder.matches("Password123!", "hashedPassword"))
                    .thenReturn(true);
            when(jwtService.generateToken(userLoginId.toString()))
                    .thenReturn("mocked-jwt-token");

            LoginResponse response = loginService.login(validRequest);

            assertNotNull(response);
            assertEquals("mocked-jwt-token", response.token());
            verify(jwtService, times(1)).generateToken(userLoginId.toString());
        }
    }

    @Test
    void login_InvalidIdentityFormat_ThrowsException() {
        try (MockedStatic<IdentityUtils> identityUtilsMock = mockStatic(IdentityUtils.class)) {
            identityUtilsMock.when(() -> IdentityUtils.validateAndFormat(anyString()))
                    .thenReturn("");

            assertThrows(FrontendException.class, () -> loginService.login(validRequest));
            verifyNoInteractions(userIdentityRepository, userLoginRepository, passwordEncoder, jwtService);
        }
    }

    @Test
    void login_UserNotFound_ThrowsException() {
        try (MockedStatic<IdentityUtils> identityUtilsMock = mockStatic(IdentityUtils.class)) {
            identityUtilsMock.when(() -> IdentityUtils.validateAndFormat("test@example.com"))
                    .thenReturn("test@example.com");

            when(userIdentityRepository.findByUserIdentityValue("test@example.com"))
                    .thenReturn(Optional.empty());

            assertThrows(FrontendException.class, () -> loginService.login(validRequest));
        }
    }

    @Test
    void login_InvalidPassword_ThrowsException() {
        try (MockedStatic<IdentityUtils> identityUtilsMock = mockStatic(IdentityUtils.class)) {
            identityUtilsMock.when(() -> IdentityUtils.validateAndFormat("test@example.com"))
                    .thenReturn("test@example.com");

            when(userIdentityRepository.findByUserIdentityValue("test@example.com"))
                    .thenReturn(Optional.of(userIdentity));
            when(userLoginRepository.findById(userLoginId))
                    .thenReturn(Optional.of(userLogin));
            when(passwordEncoder.matches("Password123!", "hashedPassword"))
                    .thenReturn(false);

            assertThrows(FrontendException.class, () -> loginService.login(validRequest));
            verify(jwtService, never()).generateToken(anyString());
        }
    }
}
