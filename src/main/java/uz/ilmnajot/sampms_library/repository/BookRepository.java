package uz.ilmnajot.sampms_library.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import uz.ilmnajot.sampms_library.Entity.Book;
import uz.ilmnajot.sampms_library.Entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "select  * from books where books.bookName like %?1%", nativeQuery = true)
    List<Book> findBookByBookName(String bookName);
    @Query(value="SELECT * FROM books WHERE books.category LIKE :category", nativeQuery = true)
    Page<Book> findAllBooksByCategory(@Param("category") String category, Pageable pageable);
//    @Query(value="SELECT * FROM books WHERE books.author_id LIKE :author_id", nativeQuery = true)
    Page<Book> findAllBooksByAuthorId(/*@Param("author_id")*/ Long author_id, Pageable pageable);


    Page<Book> findBookByQuantityLessThan(int quantity, Pageable pageable);
    Page<Book> findBookByQuantityGreaterThan(int quantity, Pageable pageable);
    Optional<Book> findBookByIdAndQuantityGreaterThan(Long id, int quantity);

    List<Book> findBooksByUser(User user);

    Optional<Book> findBookById(Long id);

    Optional<Book> findBookByIsbn(Long isbn);


}
