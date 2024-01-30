package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import uz.ilmnajot.sampms_library.base.BaseEntity;
import uz.ilmnajot.sampms_library.enums.RoleName;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Role extends BaseEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private RoleName roleName;

    private boolean deleted;

}
