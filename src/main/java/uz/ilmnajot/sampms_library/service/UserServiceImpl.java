package uz.ilmnajot.sampms_library.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.ilmnajot.sampms_library.Entity.Book;
import uz.ilmnajot.sampms_library.Entity.User;
import uz.ilmnajot.sampms_library.enums.Position;
import uz.ilmnajot.sampms_library.enums.SchoolName;
import uz.ilmnajot.sampms_library.enums.Status;
import uz.ilmnajot.sampms_library.exception.BookException;
import uz.ilmnajot.sampms_library.exception.UserException;
import uz.ilmnajot.sampms_library.model.common.ApiResponse;
import uz.ilmnajot.sampms_library.model.request.BookRequest;
import uz.ilmnajot.sampms_library.model.request.StudentRequest;
import uz.ilmnajot.sampms_library.model.request.UserRequest;
import uz.ilmnajot.sampms_library.model.response.BookResponse;
import uz.ilmnajot.sampms_library.repository.BookRepository;
import uz.ilmnajot.sampms_library.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BookRepository bookRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }


    //********************BOOK_SECTION**********************//
    @Override
    public ApiResponse getBook(Long bookId) {
        Book book = getBookById(bookId);
        BookResponse bookResponse = BookResponse.bookToDto(book);
//        BookResponse bookResponse = modelMapper.map(book, BookResponse.class);
        return new ApiResponse("success", true, bookResponse);
    }

    @Override
    public ApiResponse getBookByBookName(String bookName) {
        List<Book> bookByBookName = bookRepository.findBookByBookName(bookName);
        if (bookByBookName.isEmpty()) {
            throw new BookException("No books found", HttpStatus.NOT_FOUND);
        }
        List<BookResponse> list = bookByBookName
                .stream()
                .map(BookResponse::bookToDto)
                .toList();
        return new ApiResponse("success", true, list);
    }

    @Override
    public ApiResponse getAllBook(int page, int size) {
        Page<Book> books = bookRepository.findAll(PageRequest.of(page, size));
        if (books.isEmpty()) {
            throw new BookException("No books found", HttpStatus.NOT_FOUND);
        }
        List<BookResponse> list = books
                .stream()
                .map(BookResponse::bookToDto)
                .toList();
        return new ApiResponse("success", true, list);
    }

    @Override
    public ApiResponse getAllAvailableBook(int page, int size) {
        Page<Book> books = bookRepository.findBookByQuantityGreaterThan(0, PageRequest.of(page, size));
        if (!books.isEmpty()) {
            List<BookResponse> list = books
                    .stream()
                    .map(BookResponse::bookToDto)
                    .toList();
            return new ApiResponse("success", true, list);
        }
        throw new BookException("there is no book", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse getAllNotAvailableBook(int page, int size) {
        Page<Book> books = bookRepository.findBookByQuantityLessThan(1, PageRequest.of(page, size));
        if (!books.isEmpty()) {
            List<BookResponse> list = books
                    .stream()
                    .map(BookResponse::bookToDto)
                    .toList();
            return new ApiResponse("success", true, list);
        }
        throw new BookException("there is no book", HttpStatus.NOT_FOUND);

    }

    @Override
    public ApiResponse getAllMyBook(Long userId) {
        User user = getUserById(userId);
        List<Book> booksByUser = bookRepository.findBooksByUser(user);
        List<BookResponse> list =
                booksByUser
                        .stream()
                        .map(BookResponse::bookToDto)
                        .toList();
        return new ApiResponse("success", true, list);
    }

    @Override
    public ApiResponse getBooksByCategory(int page, int size, String category) {
        Page<Book> allBooksByCategory = bookRepository.findAllBooksByCategory(category, PageRequest.of(page, size));
        if (!allBooksByCategory.isEmpty()) {
            List<BookResponse> list = allBooksByCategory
                    .stream()
                    .map(BookResponse::bookToDto)
                    .toList();
            return new ApiResponse("success", true, list);
        }
        throw new BookException("there is no book", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse getBooksByAuthor(int page, int size, Long authorId) {
        Page<Book> allBooksByAuthorId = bookRepository.findAllBooksByAuthorId(authorId, PageRequest.of(page, size));
        if (!allBooksByAuthorId.isEmpty()) {
            List<BookResponse> list = allBooksByAuthorId
                    .stream()
                    .map(BookResponse::bookToDto)
                    .toList();
            return new ApiResponse("success", true, list);
        }
        throw new BookException("there is no book", HttpStatus.NOT_FOUND);

    }

    public Book getBookById(Long bookId) {
        Optional<Book> optionalUser = bookRepository.findBookById(bookId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new BookException("there is no book with id " + bookId, HttpStatus.NOT_FOUND);
    }

    //***************************STUDENT_BY_ADMIN_PANEL********************************//
    @Override
    public ApiResponse addStudent(StudentRequest request) {
        Optional<User> userByNameAndSurname = userRepository.findUserByNameAndSurname(request.getName(), request.getSurname());
        if (userByNameAndSurname.isPresent()) {
            throw new UserException("User with name " + request.getName() + " is already in the list of users", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setGraduationStatus(false);
        user.setRoleId(request.getRoleId());
        user.setStudentGrade(request.getStudentGrade());
        user.setSchoolName(request.getSchoolName());
        user.setGender(request.getGender());
        user.setPosition(Position.STUDENT);
        user.setStatus(request.getStatus());
        return new ApiResponse("success", true, user);
    }

    @Override
    public ApiResponse getStudent(Long id) {
        User user = getUserById(id);
        StudentRequest request = StudentRequest.studentRequestToDto(user);
        return new ApiResponse("success", true, request);
    }

    @Override
    public ApiResponse getStudents() {
        List<User> users = userRepository.findAll();
        if (!users.isEmpty()) {
            List<StudentRequest> requests = users
                    .stream()
                    .map(StudentRequest::studentRequestToDto)
                    .toList();
            return new ApiResponse("success", true, requests);
        }
        throw new UserException("User not found", HttpStatus.NOT_FOUND);

    }

    @Override
    public ApiResponse getAllAvailableStudent(int page, int size) {
        List<User> userList = userRepository.findAllByGraduationStatusFalse();
        if (userList.isEmpty()) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND);
        }
        List<StudentRequest> requests = userList
                .stream()
                .map(StudentRequest::studentRequestToDto)
                .toList();
        return new ApiResponse("success", true, requests);

    }

    @Override
    public ApiResponse getAllGraduatedStudents(int page, int size) {
        List<User> userList = userRepository.findAllByGraduationStatusTrue();
        if (userList.isEmpty()) {
            throw new UserException("user not found", HttpStatus.NOT_FOUND);
        }
        List<StudentRequest> requests = userList
                .stream()
                .map(StudentRequest::studentRequestToDto)
                .toList();
        return new ApiResponse("success", true, requests);
    }

    public ApiResponse getAllStudents(int page, int size) {
        Page<User> users = userRepository.findAll(PageRequest.of(page, size));
        if (!users.isEmpty()) {
            List<StudentRequest> requests = users
                    .stream()
                    .map(StudentRequest::studentRequestToDto)
                    .toList();
            return new ApiResponse("success", true, requests);
        }
        throw new BookException("there is no student found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse deleteStudent(Long id) {
        User user = getUserById(id);
        if (user != null) {
            userRepository.deleteById(id);
            return new ApiResponse("success", true, "the student is deleted with id " + id);
        }
        throw new UserException("the student is not found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse updateStudent(StudentRequest request, Long studentId) {
        User user = getUserById(studentId);
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setStudentGrade(request.getStudentGrade());
        user.setSchoolName(request.getSchoolName());
        user.setGender(request.getGender());
        user.setPosition(request.getPosition());
        StudentRequest studentRequest = StudentRequest.studentRequestToDto(user);
        return new ApiResponse("success", true, studentRequest);
    }

    @Override
    public ApiResponse graduateStudent(StudentRequest request, Long userId) {
        User user = getUserById(userId);
        if (user.getStatus().equals(Status.NOT_IN_DEPT)) {
            user.setGraduationStatus(true);
            User savedUser = userRepository.save(user);
            StudentRequest toDto = StudentRequest.studentRequestToDto(savedUser);
            return new ApiResponse("success", true, toDto);
        }
        throw new UserException("the student should return the books" + List.of(request.getBookId()), HttpStatus.BAD_REQUEST);
    }

    @Override
    public ApiResponse getBookToStudent(Long bookId, Long userId) {
        Optional<Book> bookOptional = bookRepository.findBookByIdAndQuantityGreaterThan(bookId, 0);
        Book book = bookOptional.get();
        User user = getUserById(userId);

        user.setUserId(userId);
        book.setBookId(bookId);
        book.setQuantity(book.getQuantity() - 1);
        user.setBorrowedBook(user.getBorrowedBook() + 1);
        user.setStatus(Status.BORROWED);
        bookRepository.save(book);
        User savedStudent = userRepository.save(user);
        StudentRequest request = StudentRequest.studentRequestToDto(savedStudent);
        return new ApiResponse("success", true, request);
    }

    @Override
    public ApiResponse getBooksToStudent(Long bookId, Long userId, int amount) {

        return null;
    }

    @Override
    public ApiResponse returnBook(Long studentId, Long bookId) {

        return null;
    }

    public User getUserById(Long userId) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return optionalUser.get();
        }
        throw new UserException("there is no user with id " + userId, HttpStatus.NOT_FOUND);
    }


    //**********************BOOK_SECTION*******************************//

    @Override
    public ApiResponse addBook(BookRequest request) {
        Optional<Book> optionalBook = bookRepository.findBookByIsbn(request.getIsbn());
        if (optionalBook.isEmpty()) {
            Book book = new Book();
            book.setName(request.getName());
            book.setIsbn(request.getIsbn());
            book.setDescription(request.getDescription());
            book.setQuantity(request.getQuantity());
            book.setAuthorId(request.getAuthorId());
            book.setCategory(request.getCategory());
            Book savedBook = bookRepository.save(book);
            BookResponse bookResponse = BookResponse.bookToDto(savedBook);
            return new ApiResponse("success", true, bookResponse);
        }
        throw new BookException("the book is already registered", HttpStatus.EXPECTATION_FAILED);
    }

    @Override
    public ApiResponse incrementBook(Long bookId, int incrementAmount) {
        Book book = getBookById(bookId);
        book.setQuantity(book.getQuantity() + incrementAmount);
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse = BookResponse.bookToDto(savedBook);
        return new ApiResponse("success", true, bookResponse);
    }


    @Override
    public ApiResponse decrementBook(Long bookId, int decrementAmount) {
        Book book = getBookById(bookId);
        int quantity = book.getQuantity();
        if (quantity >= decrementAmount) {
            book.setQuantity(book.getQuantity() - decrementAmount);
            Book savedBook = bookRepository.save(book);
            BookResponse bookResponse = BookResponse.bookToDto(savedBook);
            return new ApiResponse("success", true, bookResponse);
        }
        throw new BookException("you cannot take more than number of books available", HttpStatus.BAD_REQUEST);

    }


    @Override
    public ApiResponse deleteBook(Long id) {
        Book book = getBookById(id);
        if (book != null) {
            bookRepository.deleteById(id);
            return new ApiResponse("success", true, "Book deleted successfully");
        }
        throw new BookException("the book is not deleted or found", HttpStatus.NOT_FOUND);
    }

    @Override
    public ApiResponse updateBook(BookRequest request, Long id) {
        Book book = getBookById(id);
        book.setId(id);
        book.setName(request.getName());
        book.setIsbn(request.getIsbn());
        book.setDescription(request.getDescription());
        book.setQuantity(request.getQuantity());
        book.setAuthorId(request.getAuthorId());
        book.setCategory(request.getCategory());
        Book savedBook = bookRepository.save(book);
        BookResponse bookResponse = BookResponse.bookToDto(savedBook);
        return new ApiResponse("success", true, bookResponse);
    }

    @Override
    public ApiResponse getBookToTeacher(Long bookId, Long teacherId) {


        return null;
    }

    //**************************USER_PANEL***************************//
    @Override
    public ApiResponse addUser(UserRequest request) {
        Optional<User> userByEmail = userRepository.findUserByEmail(request.getEmail());
        if (userByEmail.isPresent()) {
            throw new UserException("User already exists or registered", HttpStatus.BAD_REQUEST);
        }
        User user = new User();
        user.setName(request.getName());
        user.setSurname(request.getSurname());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setRoleId(request.getRoleId());
        user.setSchoolName(SchoolName.SAMARQAND_SHAHRIDAGI_PREZIDENT_MAKTABI);
        user.setGender(request.getGender());
        user.setPosition(request.getPosition());
        user.setStatus(request.getStatus());
        User savedUser = userRepository.save(user);
        UserRequest userRequest = UserRequest.userToDto(savedUser);
        return new ApiResponse("success", true, userRequest);
    }

    @Override
    public ApiResponse getUser(Long id) {
        return null;
    }

    @Override
    public ApiResponse getAllUser(int page, int size) {
        return null;
    }

    @Override
    public ApiResponse findAll(int page, int size) {
        return null;
    }

    @Override
    public ApiResponse getSingleBook(Long id) {
        return null;
    }

    @Override
    public ApiResponse getAllDeletedBook(int page, int size) {
        return null;
    }

    @Override
    public ApiResponse deleteUser(Long id) {
        return null;
    }

    @Override
    public ApiResponse updateUser(Long teacherId, UserRequest request) {
        return null;
    }

    @Override
    public ApiResponse returnBookByUser(Long userId, Long bookId) {
        return null;
    }
}
