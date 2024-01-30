package uz.ilmnajot.sampms_library.service;

import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.BookRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.model.request.UserRequest;

public interface UserService {
    ApiResponse getBook(Long bookId);

    ApiResponse getBookByBookName(String bookName);

    ApiResponse findAll(int page, int size);

    ApiResponse getAllAvailableBook(int page, int size);

    ApiResponse getAllNotAvailableBook(int page, int size);

    ApiResponse getAllMyBook(Long userId);

    ApiResponse getBooksByCategory(int page, int size, String category);

    ApiResponse getBooksByAuthor(int page, int size, Long authorId);

    ApiResponse addStudent(StudentRequest request);

    ApiResponse getStudent(Long id);

    ApiResponse getStudents();

    ApiResponse getAllAvailableStudent(int page, int size);

    ApiResponse getAllGraduatedStudents(int page, int size);

    ApiResponse getAllStudents(int page, int size);

    ApiResponse deleteStudent(Long id);

    ApiResponse updateStudent(StudentRequest request, Long studentId);

    ApiResponse graduateStudent(StudentRequest request, Long userId);

    ApiResponse getBookToStudent(Long bookId, Long userId);

    ApiResponse getBooksToStudent(Long bookId, Long userId, int amount);

    ApiResponse returnBook(Long studentId, Long bookId);

    ApiResponse addBook(BookRequest request);

    ApiResponse incrementBook(Long bookId, int incrementAmount);

    ApiResponse decrementBook(Long bookId, int decrementAmount);
    ApiResponse getSingleBook(Long id);

    ApiResponse getBookToTeacher(Long bookId, Long teacherId);

    ApiResponse getAllBook(int page, int size);

    ApiResponse getAllDeletedBook(int page, int size);

    ApiResponse deleteBook(Long id);

    ApiResponse updateBook(BookRequest request, Long id);

    ApiResponse addUser(UserRequest request);

    ApiResponse getUser(Long id);

    ApiResponse getAllUser(int page, int size);

    ApiResponse deleteUser(Long id);

    ApiResponse updateUser(Long teacherId, UserRequest request);

    ApiResponse returnBookByUser(Long userId, Long bookId);
}
