package com.lms.lms.controller.books;

import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import com.lms.lms.service.BooksService;
import com.lms.lms.dto.BooksInputDTO;
import com.lms.lms.dto.BooksResponseDTO;
import com.lms.lms.model.BookStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BooksController {

    @Autowired
    private BooksService booksService;

    // Create Book
    @PostMapping
    public ResponseEntity<BooksResponseDTO> createBook(@Valid @RequestBody BooksInputDTO booksInputDTO) {
        try {
            BooksResponseDTO createdBook = booksService.createBook(booksInputDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Get All Books
    @GetMapping
    public ResponseEntity<List<BooksResponseDTO>> getAllBooks() {
        List<BooksResponseDTO> books = booksService.getAllBooks();
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    // Get Book By Id
    @GetMapping("/{id}")
    public ResponseEntity<BooksResponseDTO> getBookById(@PathVariable String id) {
        Optional<BooksResponseDTO> book = booksService.getBookById(id);
        return book.map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Update Book
    @PutMapping("/{id}")
    public ResponseEntity<BooksResponseDTO> updateBook(@PathVariable String id,
                                                       @Valid @RequestBody BooksInputDTO booksInputDTO) {
        Optional<BooksResponseDTO> updatedBook = booksService.updateBook(id, booksInputDTO);
        return updatedBook.map(ResponseEntity::ok)
                          .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete Book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable String id) {
        boolean deleted = booksService.deleteBook(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    // Get Books By Library Id
    @GetMapping("/library/{libraryId}")
    public ResponseEntity<List<BooksResponseDTO>> getBooksByLibraryId(@PathVariable String libraryId) {
        List<BooksResponseDTO> books = booksService.getBooksByLibraryId(libraryId);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    // Get Books By Status
    @GetMapping("/status/{status}")
    public ResponseEntity<List<BooksResponseDTO>> getBooksByStatus(@PathVariable BookStatus status) {
        List<BooksResponseDTO> books = booksService.getBooksByStatus(status);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }
}
