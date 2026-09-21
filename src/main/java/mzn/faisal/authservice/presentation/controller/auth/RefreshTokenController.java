package mzn.faisal.authservice.presentation.controller.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.dto.auth.RefreshTokenRequest;
import mzn.faisal.authservice.business.dto.auth.login.LoginResponse;

import mzn.faisal.authservice.business.service.auth.RefreshService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class RefreshTokenController {

    private final RefreshService refreshService;

    @PostMapping("/refresh-token")
    public ResponseEntity<LoginResponse> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        LoginResponse response = refreshService.refreshToken(request);
        return ResponseEntity.ok(response);
    }
}