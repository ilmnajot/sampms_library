package uz.ilmnajot.sampms_library.model.request;

import lombok.Data;
import uz.ilmnajot.sampms_library.enums.Category;

@Data
public class BookRequest {


    private String name;

    private Long isbn;

    private String description;

    private int quantity;

    private Long authorId;

//    private Long userId;

//    private Long bookId;

//    @Enumerated(EnumType.STRING)
    private Category category;
}
