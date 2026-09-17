package mzn.faisal.authservice.presentation.controller.auth.register;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mzn.faisal.authservice.business.service.auth.register.RegisterService;
import mzn.faisal.authservice.business.dto.auth.register.RegisterRequest;
import mzn.faisal.authservice.business.dto.auth.register.RegisterResponse;
import mzn.faisal.authservice.business.dto.common.AppResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class RegisterController {

    private final RegisterService registerService;

    @PostMapping("/register")
    public ResponseEntity<AppResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request){
        RegisterResponse response = registerService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AppResponse.ok(response, "userRegisteredSuccessfully"));
    }
}
