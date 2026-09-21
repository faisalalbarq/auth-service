package mzn.faisal.authservice.business.service.auth.register;

import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.business.dto.auth.register.RegisterRequest;
import mzn.faisal.authservice.business.dto.auth.register.RegisterResponse;
import mzn.faisal.authservice.data.repository.PartyRepository;
import mzn.faisal.authservice.data.repository.UserIdentityRepository;
import mzn.faisal.authservice.data.repository.UserLoginRepository;
import mzn.faisal.authservice.data.db.entity.Party;
import mzn.faisal.authservice.data.db.entity.UserIdentity;
import mzn.faisal.authservice.data.db.entity.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class RegisterServiceTest {

    @Mock
    private UserIdentityRepository userIdentityRepository;

    @Mock
    private PartyRepository partyRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserLoginRepository userLoginRepository;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private RegisterService registerService;

    @Test
    @DisplayName("Successful Registration")
    void register_WhenValidRequest_ShouldReturnResponse() {

        RegisterRequest request = new RegisterRequest("Faisal", "faisal@example.com", 1, "Password123!");
        UUID generatedPartyId = UUID.randomUUID();
        UUID generatedLoginId = UUID.randomUUID();

        Party savedParty = new Party();
        savedParty.setPartyId(generatedPartyId);
        savedParty.setPartyName("Faisal");

        UserLogin savedLogin = new UserLogin();
        savedLogin.setUserLoginId(generatedLoginId);

        given(userIdentityRepository.existsByUserIdentityValue("faisal@example.com")).willReturn(false);
        given(partyRepository.save(any(Party.class))).willReturn(savedParty);
        given(passwordEncoder.encode(request.password())).willReturn("encodedPassword123");
        given(userLoginRepository.save(any(UserLogin.class))).willReturn(savedLogin);

        given(jwtService.generateAccessToken(generatedLoginId.toString())).willReturn("mocked-access-token");
        given(jwtService.generateRefreshToken(generatedLoginId.toString())).willReturn("mocked-refresh-token");

        RegisterResponse response = registerService.register(request);

        assertThat(response).isNotNull();
        assertThat(response.accessToken()).isEqualTo("mocked-access-token");
        assertThat(response.refreshToken()).isEqualTo("mocked-refresh-token");
        assertThat(response.partyName()).isEqualTo("Faisal");

        verify(partyRepository).save(any(Party.class));
        verify(userLoginRepository).save(any(UserLogin.class));
        verify(userIdentityRepository).save(any(UserIdentity.class));
    }
}