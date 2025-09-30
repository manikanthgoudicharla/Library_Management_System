package com.lms.lms.service;

// Service annotation
import org.springframework.stereotype.Service;
import org.apache.tomcat.jni.Library;
// Autowired import
import org.springframework.beans.factory.annotation.Autowired;

// BooksRepository import
import com.lms.lms.repository.BooksRepository;

//LibraryRepository import
import com.lms.lms.repository.LibraryRepository;

// BooksModel import
import com.lms.lms.model.BooksModel;

//LibraryModel import
import com.lms.lms.model.LibraryModel;

// Books dto imports
import com.lms.lms.dto.BooksInputDTO;
import com.lms.lms.dto.BooksResponseDTO;

// utils
import java.util.List;
import java.util.Optional;
import java.util.Date;


@Service
public class BooksService {

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    public BooksModel convertToModel(BooksInputDTO dto) {
        
        LibraryModel library = libraryRepository.findById(dto.getLibraryId())
                .orElseThrow(() -> new IllegalArgumentException("Invalid library ID"));
        
        BooksModel book = new BooksModel();
        book.setTitle(dto.getTitle());
        book.setAuthor(dto.getAuthor());
        book.setCategory(dto.getCategory());
        book.setPublicationYear(dto.getPublicationYear());
        book.setStatus(dto.getStatus());
        book.setLibrary(library);
        book.setCreatedAt(new Date());
        book.setUpdatedAt(new Date());
        return book;

    }

  public BooksResponseDTO convertToDTO(BooksModel model) {
    BooksResponseDTO dto = new BooksResponseDTO();
    dto.setId(model.getId());
    dto.setTitle(model.getTitle());
    dto.setAuthor(model.getAuthor());
    dto.setCategory(model.getCategory());
    dto.setPublicationYear(model.getPublicationYear());
    dto.setStatus(model.getStatus());
    dto.setLibraryId(model.getLibrary().getId());
    dto.setLibraryName(model.getLibrary().getName());
    dto.setCreatedAt(model.getCreatedAt());
    dto.setUpdatedAt(model.getUpdatedAt());
    return dto;
}

    // Create Book
    public BooksResponseDTO createBook(BooksInputDTO dto) {
        BooksModel book = convertToModel(dto);
        BooksModel savedBook = booksRepository.save(book);
        return convertToDTO(savedBook);
    }

    // Get All Books
    public List<BooksResponseDTO> getAllBooks() {
        return booksRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Get Book By id 
    public Optional<BooksResponseDTO> getBookById(String id) {
        return booksRepository.findById(id).map(this::convertToDTO);
    }
    
    // Update Book
    public Optional<BooksResponseDTO> updateBook(String id, BooksInputDTO dto) {
        return booksRepository.findById(id).map(existingBook -> {
            existingBook.setTitle(dto.getTitle());
            existingBook.setAuthor(dto.getAuthor());
            existingBook.setCategory(dto.getCategory());
            existingBook.setPublicationYear(dto.getPublicationYear());
            existingBook.setStatus(dto.getStatus());
            
            LibraryModel library = libraryRepository.findById(dto.getLibraryId())
                    .orElseThrow(() -> new IllegalArgumentException("Invalid library ID"));
            existingBook.setLibrary(library);
            
            existingBook.setUpdatedAt(new Date());
            return convertToDTO(booksRepository.save(existingBook));
        });
    }

    // Delete Book
    public boolean deleteBook(String id) {
        if (booksRepository.existsById(id)) {
            booksRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Get Books By Library Id
    public List<BooksResponseDTO> getBooksByLibraryId(String libraryId) {
        return booksRepository.findByLibraryId(libraryId)
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Get Books By Status
    public List<BooksResponseDTO> getBooksByStatus(com.lms.lms.model.BookStatus status) {
        return booksRepository.findAll()
                .stream()
                .filter(book -> book.getStatus() == status)
                .map(this::convertToDTO)
                .toList();
    }
}
