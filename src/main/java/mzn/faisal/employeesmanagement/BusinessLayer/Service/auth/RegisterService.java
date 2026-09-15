package mzn.faisal.employeesmanagement.BusinessLayer.Service.auth;

import lombok.RequiredArgsConstructor;
import mzn.faisal.employeesmanagement.BusinessLayer.Service.security.JwtService;
import mzn.faisal.employeesmanagement.BusinessLayer.config.FrontendException;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterRequest;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterResponse;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.Party;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserIdentity;
import mzn.faisal.employeesmanagement.DataLayer.db.Entity.UserLogin;
import mzn.faisal.employeesmanagement.DataLayer.Repository.PartyRepository;
import mzn.faisal.employeesmanagement.DataLayer.Repository.UserIdentityRepository;
import mzn.faisal.employeesmanagement.DataLayer.Repository.UserLoginRepository;
import mzn.faisal.employeesmanagement.utils.UserIdentityUtils.IdentityUtils;
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

        String token = jwtService.generateToken(userLogin.getUserLoginId().toString());

        return new RegisterResponse(token, party.getPartyName());
    }

}
