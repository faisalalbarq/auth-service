package mzn.faisal.authservice.business.service.auth.register;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.security.JwtService;
import mzn.faisal.authservice.presentation.exception.FrontendException;
import mzn.faisal.authservice.business.dto.auth.register.RegisterRequest;
import mzn.faisal.authservice.business.dto.auth.register.RegisterResponse;
import mzn.faisal.authservice.data.db.entity.Party;
import mzn.faisal.authservice.data.db.entity.UserIdentity;
import mzn.faisal.authservice.data.db.entity.UserLogin;
import mzn.faisal.authservice.data.repository.PartyRepository;
import mzn.faisal.authservice.data.repository.UserIdentityRepository;
import mzn.faisal.authservice.data.repository.UserLoginRepository;
import mzn.faisal.authservice.utils.identity.IdentityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RegisterService {

    private final UserIdentityRepository userIdentityRepository;
    private final PartyRepository partyRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserLoginRepository userLoginRepository;
    private final JwtService jwtService;

    @Transactional
    public RegisterResponse register(RegisterRequest request){

        String formattedIdentity = IdentityUtils.validateAndFormat(request.identityValue());

        if (formattedIdentity.isBlank()) {
            throw new FrontendException("invalidIdentityFormat", HttpStatus.BAD_REQUEST);
        }

        if(userIdentityRepository.existsByUserIdentityValue(formattedIdentity)) {
            throw new FrontendException("userAlreadyExists");
        }

        Party party = new Party();
        party.setPartyName(request.name());
        party = partyRepository.save(party);


        UserLogin userLogin = new UserLogin();
        userLogin.setPartyId(party.getPartyId());
        userLogin.setPassword(passwordEncoder.encode(request.password()));
        userLogin = userLoginRepository.save(userLogin);


        int userIdentityTypeId = formattedIdentity.contains("@") ? 1 : 2;

        UserIdentity userIdentity = new UserIdentity();
        userIdentity.setUserLoginId(userLogin.getUserLoginId());
        userIdentity.setUserIdentityTypeId(userIdentityTypeId);
        userIdentity.setUserIdentityValue(formattedIdentity);
        userIdentity = userIdentityRepository.save(userIdentity);


        String accessToken = jwtService.generateAccessToken(userLogin.getUserLoginId().toString());
        String refreshToken = jwtService.generateRefreshToken(userLogin.getUserLoginId().toString());

        return new RegisterResponse(accessToken, refreshToken, party.getPartyName());
    }

}
