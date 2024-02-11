package uz.ilmnajot.sampms_library.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.LoginRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.service.AuthService;
import uz.ilmnajot.sampms_library.service.UserService;

import static uz.ilmnajot.sampms_library.utils.Constants.*;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;
    private final AuthService authService;

    public AuthController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }
//    @PostMapping(REGISTER_STUDENT)
//    public HttpEntity<ApiResponse> registerStudent(@RequestBody StudentRequest request) {
//        ApiResponse user = userService.addStudent(request);
//        return user !=                                                                                                                                                                null
//                ? ResponseEntity.ok(user)
//                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }
    @PostMapping(REGISTER_STUDENT)
    public HttpEntity<ApiResponse> registerUser(@RequestBody StudentRequest request) {
        ApiResponse user = authService.registerStudent(request);
        return user != null
                ? ResponseEntity.ok(user)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(VERIFY_STUDENT)
    public HttpEntity<ApiResponse> verifyUser(@RequestParam String email, @RequestParam String emailCode) {
        ApiResponse user = authService.verifyUser(email, emailCode);
        return user != null
                ? ResponseEntity.status(HttpStatus.NOT_FOUND).body(user)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(LOGIN)
    public HttpEntity<ApiResponse> login(@RequestBody LoginRequest request) {
        ApiResponse login = authService.login(request);
        return login != null
                ? ResponseEntity.ok(login)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }



}
