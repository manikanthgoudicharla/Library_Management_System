package com.lms.lms.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.lms.repository.LibraryRepository;
import com.lms.lms.model.LibraryModel;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    /**
     * Create a new library
     */
    public LibraryModel createLibrary(LibraryModel library) {
        if (library == null) {
            throw new IllegalArgumentException("Library data must not be null");
        }
        return libraryRepository.save(library);
    }

    /**
     * Get all libraries
     */
    public List<LibraryModel> getAllLibraries() {
        return libraryRepository.findAll();
    }

    /**
     * Get library by ID
     */
    public Optional<LibraryModel> getLibraryById(String id) {
        if (id == null || id.isEmpty()) {
            return Optional.empty();
        }
        return libraryRepository.findById(id);
    }

    /**
     * Update library by ID
     */
    public Optional<LibraryModel> updateLibrary(String id, LibraryModel libraryDetails) {
        if (id == null || id.isEmpty() || libraryDetails == null) {
            return Optional.empty();
        }

        return libraryRepository.findById(id).map(existingLibrary -> {
            existingLibrary.setName(libraryDetails.getName());
            existingLibrary.setAddress(libraryDetails.getAddress());
            existingLibrary.setStatus(libraryDetails.getStatus());
            existingLibrary.setContactNumber(libraryDetails.getContactNumber());
            existingLibrary.setEmail(libraryDetails.getEmail());
            existingLibrary.setTotalCapacity(libraryDetails.getTotalCapacity());
            existingLibrary.setTotalBooks(libraryDetails.getTotalBooks());
            existingLibrary.setOpeningTime(libraryDetails.getOpeningTime());
            existingLibrary.setClosingTime(libraryDetails.getClosingTime());
            return libraryRepository.save(existingLibrary);
        });
    }

    /**
     * Delete library by ID
     */
    public boolean deleteLibrary(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }

        return libraryRepository.findById(id).map(library -> {
            libraryRepository.delete(library);
            return true;
        }).orElse(false);
    }
}
