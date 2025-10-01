package com.lms.lms.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.lms.lms.repository.UserRepository;
import com.lms.lms.repository.BooksRepository;
import com.lms.lms.repository.BorrowDetailsRepository;
import com.lms.lms.repository.LibraryRepository;

import com.lms.lms.model.UserModel;
import com.lms.lms.model.BooksModel;
import com.lms.lms.model.BorrowDetailsModel;
import com.lms.lms.model.LibraryModel;
import com.lms.lms.model.BookStatus;

import java.util.Date;
import java.util.List;

@Service
public class BorrowService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private BorrowDetailsRepository borrowDetailsRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    // Convert milliseconds to test days (30 seconds = 1 day)
    private long calculateLateDays(Date issueDate, Date returnDate) {
        long diffInMillis = returnDate.getTime() - issueDate.getTime();
        long diffInSeconds = diffInMillis / 1000;
        return Math.max(diffInSeconds / 30, 0);
    }

    // Calculate late fee (first day free)
    private int calculateLateFee(long lateDays, int libLateFee) {
        if (lateDays <= 1) return 0;
        return (int) ((lateDays - 1) * libLateFee);
    }

    // Borrow books
    public void borrowBook(String userId, String bookId, int borrowCount) {
        if (borrowCount <= 0) throw new IllegalArgumentException("Borrow count must be greater than zero.");

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        BooksModel book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        if (book.getBookCount() < borrowCount) {
            throw new IllegalArgumentException("Not enough books available.");
        }

        // Update book count and status
        book.setBookCount(book.getBookCount() - borrowCount);
        book.setStatus(book.getBookCount() == 0 ? BookStatus.BORROWED : BookStatus.AVAILABLE);
        booksRepository.save(book);

        // Update user's borrowed books list
        List<BooksModel> userBooks = user.getBooks();
        if (userBooks == null) userBooks = new java.util.ArrayList<>();
        for (int i = 0; i < borrowCount; i++) userBooks.add(book);
        user.setBooks(userBooks);
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        // Check if a borrow record exists for this book
        BorrowDetailsModel borrowDetails = borrowDetailsRepository
                .findTopByUserNameAndBookNameOrderByIssueDateDesc(user.getFirstName(), book.getTitle())
                .orElse(null);

        if (borrowDetails != null && borrowDetails.getReturnDate() == null) {
            borrowDetails.setBorrowCount(borrowDetails.getBorrowCount() + borrowCount);
        } else {
            borrowDetails = new BorrowDetailsModel();
            borrowDetails.setUserName(user.getFirstName());
            borrowDetails.setBookName(book.getTitle());
            borrowDetails.setLibraryName(book.getLibrary().getName());
            borrowDetails.setBorrowCount(borrowCount);
            borrowDetails.setLateFee(0);
            borrowDetails.setIssueDate(new Date());
            borrowDetails.setReturnDate(null);
        }

        borrowDetailsRepository.save(borrowDetails);
    }

    // Return books
    public void returnBook(String userId, String bookId, int returnCount) {
        if (returnCount <= 0) throw new IllegalArgumentException("Return count must be greater than zero.");

        UserModel user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid User ID"));

        BooksModel book = booksRepository.findById(bookId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid Book ID"));

        List<BooksModel> userBooks = user.getBooks();
        if (userBooks == null || userBooks.isEmpty())
            throw new IllegalArgumentException("User has no borrowed books.");

        int removedCount = 0;
        for (int i = 0; i < userBooks.size() && removedCount < returnCount; i++) {
            if (userBooks.get(i).getId().equals(bookId)) {
                userBooks.remove(i);
                removedCount++;
                i--; // Adjust index after removal
            }
        }

        if (removedCount == 0) throw new IllegalArgumentException("No borrowed books to return.");

        // Update book
        book.setBookCount(book.getBookCount() + removedCount);
        book.setStatus(BookStatus.AVAILABLE);
        booksRepository.save(book);

        user.setBooks(userBooks);
        user.setUpdatedAt(new Date());
        userRepository.save(user);

        BorrowDetailsModel borrowDetails = borrowDetailsRepository
                .findTopByUserNameAndBookNameOrderByIssueDateDesc(user.getFirstName(), book.getTitle())
                .orElseThrow(() -> new IllegalArgumentException("No borrow record found for this book"));

        borrowDetails.setBorrowCount(borrowDetails.getBorrowCount() - removedCount);

        if (borrowDetails.getBorrowCount() <= 0) {
            borrowDetails.setReturnDate(new Date());
            LibraryModel library = libraryRepository.findById(book.getLibrary().getId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid Library ID"));

            long lateDays = calculateLateDays(borrowDetails.getIssueDate(), borrowDetails.getReturnDate());
            borrowDetails.setLateFee(calculateLateFee(lateDays, library.getLateFee()));
        }

        borrowDetailsRepository.save(borrowDetails);
    }
}
