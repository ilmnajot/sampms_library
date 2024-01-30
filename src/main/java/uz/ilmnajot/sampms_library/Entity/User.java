package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.sampms_library.base.BaseEntity;
import uz.ilmnajot.sampms_library.enums.*;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends BaseEntity {

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean graduationStatus = false;

    private int gmailCode;

    private int borrowedBook;

    @OneToOne
    private Role role;

    @OneToOne
//    @JoinTable(
//            name = "user_books",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private Book book;

    @Column(name = "book_id", insertable = false, updatable = false)
    private Long bookId;

    @Column(name = "userId", insertable = false, updatable = false)
    private Long userId;

    @Column(updatable = false, insertable = false, name = "role_id")
    private Long RoleId;

    //**************ENUMS****************//

    @Enumerated(EnumType.STRING)
    private StudentGrade studentGrade;

    @Enumerated(EnumType.STRING)
    private SchoolName schoolName;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Enumerated(EnumType.STRING)
    private Position position;

    @Enumerated(EnumType.STRING)
    private Status status;

}

