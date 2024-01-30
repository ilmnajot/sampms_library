package uz.ilmnajot.sampms_library.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.ilmnajot.sampms_library.Entity.Book;
import uz.ilmnajot.sampms_library.enums.Category;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookResponse {

    private Long id;

    private String name;

    private Long isbn;

    private String description;

    private int quantity;

    private Long authorId;

    private Category category;

    public BookResponse(BookResponse bookResponse) {

    }

    public static BookResponse bookToDto(Book book){
    BookResponse response = new BookResponse();
    response.setId(book.getId());
    response.setName(book.getName());
    response.setIsbn(book.getIsbn());
    response.setDescription(book.getDescription());
    response.setQuantity(book.getQuantity());
    response.setAuthorId(book.getAuthorId());
    response.setCategory(book.getCategory());
    return response;
    }

}
