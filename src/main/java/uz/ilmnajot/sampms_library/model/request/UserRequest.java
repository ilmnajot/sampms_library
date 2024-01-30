package uz.ilmnajot.sampms_library.model.request;

import lombok.Data;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.enums.Gender;
import uz.ilmnajot.sampms_library.enums.Position;
import uz.ilmnajot.sampms_library.enums.SchoolName;
import uz.ilmnajot.sampms_library.enums.Status;

@Data
public class UserRequest {


    private String name;

    private String surname;

    private String email;

    private String password;

    private Long roleId;
    private SchoolName schoolName;

    private Gender gender;

    private Position position;

    private Status status;
//    private boolean graduationStatus = false;

//    private int gmailCode;

//    private int borrowedBook;

//    @OneToOne

//    @OneToOne
////    @JoinTable(
////            name = "user_books",
////            joinColumns = @JoinColumn(name = "user_id"),
////            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private Book book;

//    @Column(name = "book_id", insertable = false, updatable = false)
//    private Long bookId;

//    @Column(name = "userId", insertable = false, updatable = false)
//    private Long userId;

//    @Column(updatable = false, insertable = false, name = "role_id")
//    private Long RoleId;

    //**************ENUMS****************//

//    private StudentGrade studentGrade;


    public static UserRequest userToDto(User user){
        UserRequest request = new UserRequest();
        request.setName(user.getName());
        request.setSurname(user.getSurname());
        request.setEmail(user.getEmail());
        request.setPassword(user.getPassword());
        request.setRoleId(user.getRoleId());
        request.setSchoolName(SchoolName.SAMARQAND_SHAHRIDAGI_PREZIDENT_MAKTABI);
        request.setGender(request.getGender());
        request.setPosition(request.getPosition());
        request.setStatus(Status.NOT_IN_DEPT);
        return request;

    }
}
