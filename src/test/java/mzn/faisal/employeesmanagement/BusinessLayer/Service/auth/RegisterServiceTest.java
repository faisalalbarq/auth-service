package mzn.faisal.employeesmanagement.BusinessLayer.Service.auth;

import mzn.faisal.employeesmanagement.BusinessLayer.Service.security.JwtService;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterRequest;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterResponse;
import mzn.faisal.employeesmanagement.DataLayer.Repository.PartyRepository;
import mzn.faisal.employeesmanagement.DataLayer.Repository.UserIdentityRepository;
import mzn.faisal.employeesmanagement.DataLayer.Repository.UserLoginRepository;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.Party;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserIdentity;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.UUID;


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
        given(jwtService.generateToken(generatedLoginId.toString())).willReturn("mocked-jwt-token");

        RegisterResponse response = registerService.register(request);

        assertThat(response).isNotNull();
        assertThat(response.token()).isEqualTo("mocked-jwt-token");
        assertThat(response.partyName()).isEqualTo("Faisal");

        verify(partyRepository).save(any(Party.class));
        verify(userLoginRepository).save(any(UserLogin.class));
        verify(userIdentityRepository).save(any(UserIdentity.class));
    }
}