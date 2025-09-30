package com.lms.lms.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.lms.lms.repository.LibraryRepository;
import com.lms.lms.model.LibraryModel;

import java.util.List;
import java.util.Optional;

import com.lms.lms.dto.LibraryInputDTO;
import com.lms.lms.dto.LibraryResponseDTO;
import java.util.Date;

@Service
public class LibraryService {

    @Autowired
    private LibraryRepository libraryRepository;

    public LibraryResponseDTO createLibrary(LibraryInputDTO dto) {
        LibraryModel library = convertToModel(dto);
        LibraryModel savedLibrary = libraryRepository.save(library);
        return convertToDTO(savedLibrary);
    }

    public List<LibraryResponseDTO> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public Optional<LibraryResponseDTO> getLibraryById(String id) {
        return libraryRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<LibraryResponseDTO> updateLibrary(String id, LibraryInputDTO dto) {
        return libraryRepository.findById(id).map(existingLibrary -> {
            existingLibrary.setName(dto.getName());
            existingLibrary.setAddress(dto.getAddress());
            existingLibrary.setStatus(dto.getStatus());
            existingLibrary.setContactNumber(dto.getContactNumber());
            existingLibrary.setEmail(dto.getEmail());
            existingLibrary.setTotalCapacity(dto.getTotalCapacity());
            existingLibrary.setTotalBooks(dto.getTotalBooks());
            existingLibrary.setOpeningTime(dto.getOpeningTime());
            existingLibrary.setClosingTime(dto.getClosingTime());
            return convertToDTO(libraryRepository.save(existingLibrary));
        });
    }

    public boolean deleteLibrary(String id) {
        if (libraryRepository.existsById(id)) {
            libraryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private LibraryModel convertToModel(LibraryInputDTO dto) {
        LibraryModel library = new LibraryModel();
        library.setName(dto.getName());
        library.setAddress(dto.getAddress());
        library.setStatus(dto.getStatus());
        library.setContactNumber(dto.getContactNumber());
        library.setEmail(dto.getEmail());
        library.setTotalCapacity(dto.getTotalCapacity());
        library.setTotalBooks(dto.getTotalBooks());
        library.setOpeningTime(dto.getOpeningTime());
        library.setClosingTime(dto.getClosingTime());
        library.setCreatedAt(new Date());
        library.setUpdatedAt(new Date());
        return library;
    }

    private LibraryResponseDTO convertToDTO(LibraryModel library) {
        LibraryResponseDTO dto = new LibraryResponseDTO();
        dto.setId(library.getId());
        dto.setName(library.getName());
        dto.setAddress(library.getAddress());
        dto.setStatus(library.getStatus());
        dto.setContactNumber(library.getContactNumber());
        dto.setEmail(library.getEmail());
        dto.setTotalCapacity(library.getTotalCapacity());
        dto.setTotalBooks(library.getTotalBooks());
        dto.setOpeningTime(library.getOpeningTime());
        dto.setClosingTime(library.getClosingTime());
        dto.setCreatedAt(library.getCreatedAt());
        dto.setUpdatedAt(library.getUpdatedAt());
        return dto;
    }

}
