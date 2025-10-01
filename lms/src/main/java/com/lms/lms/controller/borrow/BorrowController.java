package com.lms.lms.controller.borrow;

import com.lms.lms.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    // Borrow books
    @PostMapping
    public ResponseEntity<String> borrowBook(
            @RequestParam String userId,
            @RequestParam String bookId,
            @RequestParam int borrowCount) {
        try {
            borrowService.borrowBook(userId, bookId, borrowCount);
            return ResponseEntity.status(HttpStatus.OK).body("Book(s) borrowed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error borrowing book.");
        }
    }

    // Return books
    @PostMapping("/return")
    public ResponseEntity<String> returnBook(
            @RequestParam String userId,
            @RequestParam String bookId,
            @RequestParam int returnCount) {
        try {
            borrowService.returnBook(userId, bookId, returnCount);
            return ResponseEntity.status(HttpStatus.OK).body("Book(s) returned successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error returning book.");
        }
    }
}
