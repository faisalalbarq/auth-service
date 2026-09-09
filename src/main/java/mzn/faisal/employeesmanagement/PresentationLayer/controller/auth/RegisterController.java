package mzn.faisal.employeesmanagement.PresentationLayer.controller.auth;


import lombok.RequiredArgsConstructor;
import mzn.faisal.employeesmanagement.BusinessLayer.Service.auth.RegisterService;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterRequest;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.auth.register.RegisterResponse;
import mzn.faisal.employeesmanagement.BusinessLayer.dto.common.AppResponse;
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
    public ResponseEntity<AppResponse<RegisterResponse>> register(@RequestBody RegisterRequest request){
        RegisterResponse response = registerService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(AppResponse.ok(response, "userRegisteredSuccessfully"));
    }
}
