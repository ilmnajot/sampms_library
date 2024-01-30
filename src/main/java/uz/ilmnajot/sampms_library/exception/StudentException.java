package uz.ilmnajot.sampms_library.exception;

import org.springframework.http.HttpStatus;

public class StudentException extends BaseException{
    public StudentException(String message) {
        super(message);
    }

    public StudentException(String message, HttpStatus httpStatus) {
        super(message, httpStatus);
    }

    public StudentException(HttpStatus httpStatus) {
        super(httpStatus);
    }
}
