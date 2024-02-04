package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import uz.ilmnajot.sampms_library.base.BaseEntity;
import uz.ilmnajot.sampms_library.enums.*;

import java.util.Collection;
import java.util.Collections;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "users")
@Builder
public class User extends BaseEntity implements UserDetails {

    private String name;

    private String surname;

    private String email;

    private String password;

    private boolean graduationStatus;

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

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialsNonExpired = true;
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.getName());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}

