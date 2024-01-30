package uz.ilmnajot.sampms_library.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.BookRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.model.request.UserRequest;
import uz.ilmnajot.sampms_library.service.UserService;

import javax.validation.Valid;

import static uz.ilmnajot.sampms_library.utils.Constants.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    //**********************BOOK_PANEL*************************//
    @PostMapping(ADD_BOOK)
    public HttpEntity<ApiResponse> addBook(@Valid @RequestBody BookRequest request) {
        ApiResponse book = userService.addBook(request);
        return book != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(book)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_BOOK)
    public HttpEntity<ApiResponse> getBook(@PathVariable(name = "bookId") Long bookId) {
        ApiResponse book = userService.getBook(bookId);
        return ResponseEntity.status(book.isSuccess() ? 200 : 409).body(book);
    }

    @GetMapping(GET_ALL_BOOK)
    public HttpEntity<ApiResponse> getAllBook(@RequestParam(name = "page", defaultValue = "0") int page,
                                              @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse allBook = userService.getAllBook(page, size);
        return allBook != null
                ? ResponseEntity.status(HttpStatus.FOUND).body(allBook)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(UPDATE_BOOK)
    public HttpEntity<ApiResponse> updateBook(@RequestBody BookRequest request, @PathVariable Long id) {
        ApiResponse book = userService.updateBook(request, id);
        return book != null
                ? ResponseEntity.ok(book)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping(DELETE_BOOK)
    public HttpEntity<ApiResponse> deleteBook(@PathVariable Long id) {
        ApiResponse book = userService.deleteBook(id);
        return book != null
                ? ResponseEntity.ok(book)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_AVAILABLE_BOOK)
    public HttpEntity<ApiResponse> getAllAvailableBook(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse allDeletedBook = userService.getAllAvailableBook(page, size);
        return allDeletedBook != null
                ? ResponseEntity.status(HttpStatus.FOUND).body(allDeletedBook)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_NOT_AVAILABLE_BOOK)
    public HttpEntity<ApiResponse> getAllNotAvailableBook(@RequestParam(name = "page", defaultValue = "0") int page,
                                                          @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse allDeletedBook = userService.getAllNotAvailableBook(page, size);
        return allDeletedBook != null
                ? ResponseEntity.status(HttpStatus.ACCEPTED).body(allDeletedBook)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_MY_BOOK)
    public HttpEntity<ApiResponse> getMyBook(
            @PathVariable(name = "userId") Long userId) {
        ApiResponse allMyBook = userService.getAllMyBook(userId);
        return allMyBook != null
                ? ResponseEntity.status(HttpStatus.ACCEPTED).body(allMyBook)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(SEARCH)
    public HttpEntity<ApiResponse> getBookByName(@RequestParam String bookName) {
        ApiResponse book = userService.getBookByBookName(bookName);
        return ResponseEntity.status(book.isSuccess() ? 200 : 409).body(book);
    }

    @GetMapping(GET_BOOKS_BY_CATEGORY)
    public HttpEntity<ApiResponse> getAllBooksByCategory(@RequestParam(name = "page", defaultValue = "0") int page,
                                                         @RequestParam(name = "size", defaultValue = "9") int size,
                                                         @RequestParam(name = "categoryName") String category) {
        ApiResponse booksByCategory = userService.getBooksByCategory(page, size, category);
        return booksByCategory != null
                ? ResponseEntity.status(HttpStatus.FOUND).body(booksByCategory)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(GET_BOOKS_BY_AUTHOR)
    public HttpEntity<ApiResponse> getAllBooksByAuthor(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "9") int size,
                                                       @PathVariable(name = "author_id") Long author_id) {
        ApiResponse booksByCategory = userService.getBooksByAuthor(page, size, author_id);
        return booksByCategory != null
                ? ResponseEntity.status(HttpStatus.FOUND).body(booksByCategory)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @PostMapping(RETURN_BOOK)
    public HttpEntity<ApiResponse> returnBook(
            @PathVariable(name = "studentId") Long studentId,
            @PathVariable(name = "bookId") Long bookId) {
        ApiResponse apiResponse = userService.returnBook(studentId, bookId);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


    @PostMapping(INCREASE_BOOK)
    public HttpEntity<ApiResponse> incrementBook(
            @PathVariable(name = "bookId") Long bookId,
            @RequestParam(name = "increment_amount") int increment_amount) {
        ApiResponse apiResponse = userService.incrementBook(bookId, increment_amount);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(DECREASE_BOOK)
    public HttpEntity<ApiResponse> decrementBook(
            @PathVariable(name = "bookId") Long bookId,
            @RequestParam(name = "decrement_amount") int decrement_amount) {
        ApiResponse apiResponse = userService.decrementBook(bookId, decrement_amount);
        return apiResponse != null
                ? ResponseEntity.ok(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


//    @GetMapping(GET_ALL_DELETED_BOOK)
//    public HttpEntity<ApiResponse> getAllDeletedBook(
//            @RequestParam(name = "page", defaultValue = "0") int page,
//            @RequestParam(name = "size", defaultValue = "9") int size) {
//        ApiResponse allDeletedBook = userService.getAllDeletedBook(page, size);
//        return allDeletedBook != null
//                ? ResponseEntity.ok(allDeletedBook)
//                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//    }


    //*****************************STUDENT_PANEL****************************//

    @PostMapping(ADD_STUDENT)
    public HttpEntity<ApiResponse> addStudent(@RequestBody StudentRequest request) {
        ApiResponse student = userService.addStudent(request);
        return student != null
                ? ResponseEntity.status(HttpStatus.CREATED).body(student)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_STUDENT)
    public HttpEntity<ApiResponse> getStudent(@PathVariable Long id) {
        ApiResponse student = userService.getStudent(id);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_UNGRADUATED_STUDENT)
    public ResponseEntity<ApiResponse> getAllAvailableStudent(@RequestParam(name = "page", defaultValue = "0") int page,
                                                              @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse students = userService.getAllAvailableStudent(page, size);
        return students != null
                ? ResponseEntity.ok(students)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_GRADUATED_STUDENT)
    public ResponseEntity<ApiResponse> getAllNonExistStudent(@RequestParam(name = "page", defaultValue = "0") int page,
                                                             @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse students = userService.getAllGraduatedStudents(page, size);
        return students != null
                ? ResponseEntity.ok(students)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_STUDENT)
    public ResponseEntity<ApiResponse> getAllStudent(@RequestParam(name = "page", defaultValue = "0") int page,
                                                     @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse students = userService.getAllStudents(page, size);
        return students != null
                ? ResponseEntity.ok(students)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping(DELETE_STUDENT)
    public HttpEntity<ApiResponse> deleteStudent(@PathVariable Long id) {
        ApiResponse student = userService.deleteStudent(id);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(UPDATE_STUDENT)
    public HttpEntity<ApiResponse> updateStudent(@RequestBody StudentRequest request, @PathVariable(name = "userId") Long userId) {
        ApiResponse student = userService.updateStudent(request, userId);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(GRADUATE_STUDENT)
    public HttpEntity<ApiResponse> graduateTrue(@RequestBody StudentRequest request, @PathVariable(name = "userId") Long userId) {
        ApiResponse student = userService.graduateStudent(request, userId);
        return student != null
                ? ResponseEntity.ok(student)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(BOOK_TO_STUDENT)
    public HttpEntity<ApiResponse> bookToStudent(
            @PathVariable(name = "bookId") Long bookId,
            @PathVariable(name = "userId") Long userId) {
        ApiResponse bookToStudent = userService.getBookToStudent(bookId, userId);
        return bookToStudent != null
                ? ResponseEntity.status(HttpStatus.ACCEPTED).body(bookToStudent)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(GET_SOME_BOOKS)
    public HttpEntity<ApiResponse> getBooksToStudent(
            @PathVariable(name = "bookId") Long bookId,
            @PathVariable(name = "userId") Long userId,
            @RequestParam(name = "amount") int amount) {
        ApiResponse booksToStudent = userService.getBooksToStudent(bookId, userId, amount);
        return booksToStudent != null
                ? ResponseEntity.ok(booksToStudent)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();

    }

    //*************************USER_SECTION **************************//

    @PostMapping(ADD_USER)
    public HttpEntity<ApiResponse> addUser(@RequestBody UserRequest request) {
        ApiResponse teacher = userService.addUser(request);
        return teacher != null
                ? ResponseEntity.ok(teacher)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_USER)
    public HttpEntity<ApiResponse> getUser(@PathVariable Long id) {
        ApiResponse teacher = userService.getUser(id);
        return teacher != null
                ? ResponseEntity.ok(teacher)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping(GET_ALL_USER)
    public HttpEntity<ApiResponse> getAllUsers(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "9") int size) {
        ApiResponse allTeachers = userService.getAllUser(page, size);
        return allTeachers != null
                ? ResponseEntity.status(HttpStatus.OK).body(allTeachers)
                : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @DeleteMapping(DELETE_USER)
    public HttpEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        ApiResponse teacher = userService.deleteUser(id);
        return teacher != null
                ? ResponseEntity.status(HttpStatus.OK).body(teacher)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PutMapping(UPDATE_USER)
    public HttpEntity<ApiResponse> updateUser(
            @PathVariable(name = "teacherId") Long teacherId,
            @RequestBody UserRequest request) {
        ApiResponse teacher = userService.updateUser(teacherId, request);
        return teacher != null
                ? ResponseEntity.status(HttpStatus.OK).body(teacher)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(RETURN_BOOK_USER)
    public HttpEntity<ApiResponse> returnBookByUser(
            @PathVariable(name = "userId") Long userId,
            @PathVariable Long bookId) {
        ApiResponse apiResponse = userService.returnBookByUser(userId, bookId);
        return apiResponse != null
                ? ResponseEntity.status(HttpStatus.ACCEPTED).body(apiResponse)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @PostMapping(BOOK_TO_USER)
    public HttpEntity<ApiResponse> bookToTeacher(
            @PathVariable(name = "bookId") Long bookId,
            @PathVariable(name = "userId") Long userId) {
        ApiResponse getBookToTeacher = userService.getBookToTeacher(bookId, userId);
        return getBookToTeacher != null
                ? ResponseEntity.status(HttpStatus.ACCEPTED).body(getBookToTeacher)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }


}
