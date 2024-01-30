package uz.ilmnajot.sampms_library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.sampms_library.Entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
