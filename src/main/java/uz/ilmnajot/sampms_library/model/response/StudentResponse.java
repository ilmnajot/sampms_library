package uz.ilmnajot.sampms_library.model.response;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToOne;
import lombok.Data;
import uz.ilmnajot.sampms_library.Entity.Book;
import uz.ilmnajot.sampms_library.Entity.Role;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.enums.*;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;

@Data
public class StudentResponse {

    private Long id;

    private String name;

    private String surname;

    private String email;

//    private String password;

    private boolean graduationStatus;

//    private int gmailCode;

//    private int borrowedBook;

//    private Role role;

//    private Book book;

//    private Long bookId;

//    private Long userId;

    private Long RoleId;

    //**************ENUMS****************//

    private StudentGrade studentGrade;

    private SchoolName schoolName;

    private Gender gender;

    private Position position;

    private Status status;

    public static StudentResponse studentResponseToDto(User user){
        StudentResponse response = new StudentResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setEmail(user.getEmail());
        response.setGraduationStatus(user.isGraduationStatus());
        response.setRoleId(user.getRoleId());
        response.setStudentGrade(user.getStudentGrade());
        response.setSchoolName(SchoolName.SAMARQAND_SHAHRIDAGI_PREZIDENT_MAKTABI);
        response.setGender(user.getGender());
        response.setPosition(user.getPosition());
        response.setStatus(user.getStatus());
        return response;
    }
}
