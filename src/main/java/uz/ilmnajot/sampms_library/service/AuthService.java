package uz.ilmnajot.sampms_library.service;

import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.LoginRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.model.request.UserRequest;

public interface AuthService {

    ApiResponse registerStudent(UserRequest request);

    ApiResponse verifyUser(String email, String emailCode);

    ApiResponse registerStudent(StudentRequest request);

    ApiResponse login(LoginRequest request);
}
