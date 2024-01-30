package uz.ilmnajot.sampms_library.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.sampms_library.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Optional<User> findUserByNameAndSurname(String name, String surname);
    List<User> findAllByGraduationStatusFalse();
    List<User> findAllByGraduationStatusTrue();

    Optional<User> findUserByEmail(String email);
}
