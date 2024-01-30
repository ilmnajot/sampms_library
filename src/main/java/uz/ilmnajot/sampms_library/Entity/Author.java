package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.*;
import uz.ilmnajot.sampms_library.base.BaseEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity(name = "authors")
public class Author extends BaseEntity {

    private String name;

    private String surname;

    private String email;

    @OneToMany
    private List<Book> books;

}
