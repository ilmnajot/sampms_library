package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.*;
import lombok.*;
import uz.ilmnajot.sampms_library.base.BaseEntity;
import uz.ilmnajot.sampms_library.enums.Category;

import javax.validation.constraints.Size;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder@Entity(name = "books")
public class Book extends BaseEntity {

    private String name;

    @Size(max = 25, min = 13, message = "isbn code of the book cannot be less than 13 digits")
    private Long isbn;

    private String description;

    private int quantity;

    @ManyToOne
    private Author author;

    @Column(name = "author_id", insertable = false, updatable = false)
    private Long authorId;

    @ManyToOne
    private User user;

    @Column(name = "book_id", insertable = false, updatable = false)
    private Long bookId;

    @Enumerated(EnumType.STRING)
    private Category category;



}
