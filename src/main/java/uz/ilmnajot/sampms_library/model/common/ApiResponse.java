package uz.ilmnajot.sampms_library.model.common;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiResponse {

    private String message;
    private boolean success;
    private Object info;

    public ApiResponse(String message, boolean success, Object info){
        this.message = message;
        this.success = success;
        this.info = info;
    }
    public ApiResponse(boolean success, Object info){
        this.success = success;
        this.info = info;
    }
}
