package mzn.faisal.authservice.presentation.controller.auth.login;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.login.LoginRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;
import mzn.faisal.authservice.business.dto.common.AppResponse;
import mzn.faisal.authservice.business.service.auth.login.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<AppResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request){

        LoginResponse response = loginService.login(request);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AppResponse.ok(response, "userLoggedInSuccessfully"));
    }
}
