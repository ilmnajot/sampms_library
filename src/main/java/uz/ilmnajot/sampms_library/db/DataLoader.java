package uz.ilmnajot.sampms_library.db;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.ilmnajot.sampms_library.Entity.Book;
import uz.ilmnajot.sampms_library.Entity.Role;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.enums.*;
import uz.ilmnajot.sampms_library.repository.BookRepository;
import uz.ilmnajot.sampms_library.repository.RoleRepository;
import uz.ilmnajot.sampms_library.repository.UserRepository;

@Component
public class DataLoader implements CommandLineRunner {

    @Value("${spring.sql.init.mode}")
    private String mode;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;
    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public DataLoader(UserRepository userRepository, BookRepository bookRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }


    @Override
    public void run(String... args) throws Exception {

        if (mode.equals("always")) {
            Role teacher_role = roleRepository.save(
                    Role
                            .builder()
                            .name("Elbek")
                            .roleName(RoleName.TEACHER)
                            .deleted(false)
                            .build());
            Book book = bookRepository.save(
                    Book.builder()
                            .name("book name")
                            .isbn(123456789345L)
                            .description("book description")
                            .quantity(50)
                            .category(Category.CLASSIC)
                            .build());


            User teacher_user = userRepository.save(
                    User
                            .builder()
                            .name("Elbek")
                            .surname("Umar")
                            .email("example@gmail.com")
                            .password(passwordEncoder.encode("password"))
                            .password("password")
                            .graduationStatus(false)
                            .gmailCode(12345)
                            .borrowedBook(2)
                            .role(teacher_role)
                            .bookId(1L)
                            .schoolName(SchoolName.SAMARQAND_SHAHRIDAGI_PREZIDENT_MAKTABI)
                            .position(Position.TEACHER)
                            .status(Status.BORROWED)
                            .build());

        }

    }
}
