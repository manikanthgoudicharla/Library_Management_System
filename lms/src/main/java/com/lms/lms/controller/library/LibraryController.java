package com.lms.lms.controller.library;

import org.springframework.web.bind.annotation.*;
import com.lms.lms.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.lms.model.LibraryModel;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // Get All Libraries
    @GetMapping
    public ResponseEntity<List<LibraryModel>> getAllLibraries() {
        List<LibraryModel> libraries = libraryService.getAllLibraries();
        if (libraries.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(libraries);
    }

    // Get Library by ID
    @GetMapping("/{id}")
    public ResponseEntity<LibraryModel> getLibraryById(@PathVariable String id) {
        Optional<LibraryModel> libraryOpt = libraryService.getLibraryById(id);
        return libraryOpt
                .map(library -> ResponseEntity.ok(library))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Create Library
    @PostMapping
    public ResponseEntity<LibraryModel> createLibrary(@RequestBody LibraryModel library) {
        try {
            LibraryModel createdLibrary = libraryService.createLibrary(library);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Update Library By ID
    @PutMapping("/{id}")
    public ResponseEntity<LibraryModel> updateLibrary(@PathVariable String id, @RequestBody LibraryModel libraryDetails) {
        Optional<LibraryModel> updatedLibrary = libraryService.updateLibrary(id, libraryDetails);
        return updatedLibrary
                .map(library -> ResponseEntity.ok(library))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete Library By ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable String id) {
        boolean isDeleted = libraryService.deleteLibrary(id);
        if (isDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
