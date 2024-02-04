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

@Data
public class UserResponse {
    private Long id;

    private String name;

    private String surname;

    private String email;

//    private String password;

//    private boolean graduationStatus;

//    private int gmailCode;

    private int borrowedBook;

//    @OneToOne
//    private Role role;

//    @OneToOne
//    @JoinTable(
//            name = "user_books",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
//    private Book book;

//    @Column(name = "book_id", insertable = false, updatable = false)
//    private Long bookId;

//    @Column(name = "userId", insertable = false, updatable = false)
//    private Long userId;

    //    @Column(updatable = false, insertable = false, name = "role_id")
    private Long RoleId;

    //**************ENUMS****************//

//    private StudentGrade studentGrade;

    private SchoolName schoolName;

    private Gender gender;

    private Position position;

    private Status status;

    public static UserResponse userResponseToDto(User user) {
        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setEmail(user.getEmail());
        response.setBorrowedBook(user.getBorrowedBook());
        response.setRoleId(user.getRoleId());
        response.setSchoolName(user.getSchoolName());
        response.setGender(user.getGender());
        response.setPosition(user.getPosition());
        response.setStatus(user.getStatus());

        return response;
    }
}
