package com.lms.lms.controller.library;

import org.springframework.web.bind.annotation.*;
import com.lms.lms.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;


import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.lms.lms.dto.LibraryInputDTO;
import com.lms.lms.dto.LibraryResponseDTO;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    @GetMapping
    public ResponseEntity<List<LibraryResponseDTO>> getAllLibraries() {
        List<LibraryResponseDTO> libraries = libraryService.getAllLibraries();
        if (libraries.isEmpty()) return ResponseEntity.noContent().build();
        return ResponseEntity.ok(libraries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LibraryResponseDTO> getLibraryById(@PathVariable String id) {
        return libraryService.getLibraryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<LibraryResponseDTO> createLibrary(@RequestBody LibraryInputDTO libraryDTO) {
        try {
            LibraryResponseDTO createdLibrary = libraryService.createLibrary(libraryDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLibrary);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<LibraryResponseDTO> updateLibrary(@PathVariable String id,
                                                            @RequestBody LibraryInputDTO libraryDTO) {
        return libraryService.updateLibrary(id, libraryDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLibrary(@PathVariable String id) {
        if (libraryService.deleteLibrary(id)) return ResponseEntity.noContent().build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<LibraryResponseDTO>> getLibrariesByStatus(@PathVariable String status) {
        List<LibraryResponseDTO> libraries = libraryService.getLibrariesByStatus(status);
        return ResponseEntity.ok(libraries);
    }


}
