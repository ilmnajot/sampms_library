package uz.ilmnajot.sampms_library.service;

import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.LoginRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;

public interface AuthService {

    ApiResponse registerUser(StudentRequest request);
    ApiResponse verifyUser(String email, String emailCode);

    ApiResponse login(LoginRequest request);
}
