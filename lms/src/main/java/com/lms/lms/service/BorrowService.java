package com.lms.lms.service;

import com.lms.lms.repository.UserRepository;
import com.lms.lms.repository.BooksRepository;
import com.lms.lms.model.UserModel;
import com.lms.lms.model.BooksModel;
import com.lms.lms.model.BookStatus;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BooksRepository booksRepository;

    public void borrowBook(String userId, String bookId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        BooksModel book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        if (book.getStatus() != BookStatus.AVAILABLE) {
            throw new IllegalArgumentException("Book is already borrowed or unavailable.");
        }

        book.setStatus(BookStatus.BORROWED);
        booksRepository.save(book);

        List<BooksModel> userBooks = user.getBooks();
        if (userBooks == null) {
            userBooks = new java.util.ArrayList<>();
        }
        userBooks.add(book);
        user.setBooks(userBooks);

        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }

    public void returnBook(String userId, String bookId) {
        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        BooksModel book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        if (book.getStatus() != BookStatus.BORROWED) {
            throw new IllegalArgumentException("Book is not currently borrowed.");
        }

        // Remove book from user's borrowed list
        List<BooksModel> userBooks = user.getBooks();
        if (userBooks == null || !userBooks.removeIf(b -> b.getId().equals(bookId))) {
            throw new IllegalArgumentException("This book is not borrowed by the user.");
        }

        // Update book status
        book.setStatus(BookStatus.AVAILABLE);
        booksRepository.save(book);

        user.setUpdatedAt(new Date());
        userRepository.save(user);
    }
}
