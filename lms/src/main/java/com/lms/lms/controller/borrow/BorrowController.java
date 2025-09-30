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

    // Borrow a book
    @PostMapping
    public ResponseEntity<String> borrowBook(@RequestParam String userId, @RequestParam String bookId) {
        try {
            borrowService.borrowBook(userId, bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Book borrowed successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error borrowing book.");
        }
    }

    // Return a book
    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestParam String userId, @RequestParam String bookId) {
        try {
            borrowService.returnBook(userId, bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Book returned successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error returning book.");
        }
    }
}
