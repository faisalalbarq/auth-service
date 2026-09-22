package mzn.faisal.authservice.presentation.controller.auth.logout;

import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.RefreshTokenRequest;
import mzn.faisal.authservice.business.dto.common.AppResponse;
import mzn.faisal.authservice.business.service.auth.logout.LogoutService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class LogoutController {

    private final LogoutService logoutService;

    @PostMapping("/logout")
    public ResponseEntity<AppResponse<Void>> logout(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String accessTokenHeader,
            @RequestBody(required = false) RefreshTokenRequest refreshTokenRequest
    ){
        String refreshToken = refreshTokenRequest != null ? refreshTokenRequest.refreshToken() : null;

        logoutService.logout(accessTokenHeader, refreshToken);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(AppResponse.ok(null, "userLoggedOutSuccessfully"));
    }
}

