package uz.ilmnajot.sampms_library.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetails {


    private Date timestamp;

    private String message;

    private String errorMessage;

}
