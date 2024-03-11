package uz.ilmnajot.sampms_library.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import uz.ilmnajot.sampms_library.enums.UserBookStatus;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class UserBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name ="user_id")
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", insertable = false, updatable = false)
    private Book book;

    @Column(name = "book_id")
    private Long bookId;

    @Column(name = "token_date")
    private LocalDateTime tokenDate;

    @Column(name = "returned_date")
    private LocalDateTime returnedDate;

    @Column(name = "user_book_status")
    @Enumerated(EnumType.STRING)
    private UserBookStatus userBookStatus;


}
