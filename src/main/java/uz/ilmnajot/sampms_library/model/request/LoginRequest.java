package uz.ilmnajot.sampms_library.model.request;

import lombok.Data;

@Data
public class LoginRequest {

    private String email;
    private String password;
}
