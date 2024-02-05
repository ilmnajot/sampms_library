package uz.ilmnajot.sampms_library.model.request;

import lombok.Data;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.enums.*;

@Data
public class StudentRequest {

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean graduationStatus = false;

//    private int gmailCode;

//    private int borrowedBook;

    private Long bookId;

    private Long RoleId;

    //**************ENUMS****************//

    private StudentGrade studentGrade;

    private SchoolName schoolName;

    private Gender gender;

    private Position position;

    private Status status;


}
