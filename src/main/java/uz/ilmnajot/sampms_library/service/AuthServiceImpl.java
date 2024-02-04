package uz.ilmnajot.sampms_library.service;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.exception.BaseException;
import uz.ilmnajot.sampms_library.exception.UserException;
import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.LoginRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.model.response.LoginResponse;
import uz.ilmnajot.sampms_library.repository.UserRepository;
import uz.ilmnajot.sampms_library.security.jwt.JwtProvider;

import java.util.Optional;
import java.util.Random;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;
    private final MailService mailService;

    public AuthServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, ModelMapper modelMapper, PasswordEncoder passwordEncoder, JwtProvider jwtProvider, MailService mailService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
        this.jwtProvider = jwtProvider;
        this.mailService = mailService;
    }

    @Override
    public ApiResponse registerUser(StudentRequest request) {
        Optional<User> optionalUser = userRepository.findUserByEmail(request.getEmail());
        if (optionalUser.isPresent()) {
            throw new UserException("the user already exists with email " + request.getEmail());
        }
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setGraduationStatus(request.isGraduationStatus());
        user.setRoleId(request.getRoleId());
        user.setStudentGrade(request.getStudentGrade());
        user.setSchoolName(request.getSchoolName());
        user.setGender(request.getGender());
        user.setPosition(request.getPosition());
        user.setStatus(request.getStatus());

        int randomNumber = new Random().nextInt(999999);
        user.setGmailCode(Integer.parseInt(String.valueOf(randomNumber).substring(0, 4)));
        mailService.sendMail(user.getEmail(), String.valueOf(user.getGmailCode()));
        userRepository.save(user);
        return new ApiResponse("success", true, "4-digit code has been sent to you email address, please verify");
    }

    @Override
    public ApiResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()));
        String token = jwtProvider.generateToken(request.getEmail());
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(token);
        return new ApiResponse("success", true, loginResponse);
    }

    @Override
    public ApiResponse verifyUser(String email, String emailCode) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new UserException("User does not exist");
        }
        User user = optionalUser.get();
        if (emailCode.equals(user.getGmailCode())) {
            user.setEnabled(true);
            userRepository.save(user);
            return new ApiResponse("success", true, "gmail has been verified successfully");
        }
        throw new BaseException("code does not match", HttpStatus.BAD_REQUEST);
    }
}
