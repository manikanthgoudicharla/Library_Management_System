package com.lms.lms.service;

// Service
import org.springframework.stereotype.Service;

// User Repository
import com.lms.lms.repository.UserRepository;

// Books Repository
import com.lms.lms.repository.BooksRepository;

// Models
import com.lms.lms.model.UserModel;
import com.lms.lms.model.BooksModel;

// DTOs
import com.lms.lms.dto.UserInputDTO;
import com.lms.lms.dto.UserResponseDTO;

// Beans
import org.springframework.beans.factory.annotation.Autowired;

// Utils
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BooksRepository booksRepository;

    // Convert InputDTO -> Model
    public UserModel convertToModel(UserInputDTO dto) {
        List<BooksModel> books = booksRepository.findAllById(dto.getBookIds());

        if (books.size() != dto.getBookIds().size()) {
            throw new IllegalArgumentException("One or more Book IDs are invalid");
        }

        UserModel user = new UserModel();
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setContactNumber(dto.getContactNumber());
        user.setRole(dto.getRole());
        user.setStatus(dto.getStatus());
        user.setBooks(books); // List<BooksModel>
        user.setCreatedAt(new Date());
        user.setUpdatedAt(new Date());

        return user;
    }

    // Convert Model -> ResponseDTO
    public UserResponseDTO convertToDTO(UserModel user) {
        UserResponseDTO dto = new UserResponseDTO();

        dto.setId(user.getId());
        dto.setFirstName(user.getFirstName());
        dto.setLastName(user.getLastName());
        dto.setEmail(user.getEmail());
        dto.setContactNumber(user.getContactNumber());
        dto.setRole(user.getRole());
        dto.setStatus(user.getStatus());

        // Convert List<BooksModel> → List<String> (titles)
        if (user.getBooks() != null && !user.getBooks().isEmpty()) {
            List<String> bookTitles = user.getBooks()
                    .stream()
                    .map(BooksModel::getTitle)
                    .toList();
            dto.setBorrowedBookTitles(bookTitles);
        }

        dto.setCreatedAt(user.getCreatedAt());
        dto.setUpdatedAt(user.getUpdatedAt());

        return dto;
    }

    // Create User
    public UserResponseDTO createUser(UserInputDTO dto) {
        UserModel userModel = convertToModel(dto);
        UserModel savedUser = userRepository.save(userModel);
        return convertToDTO(savedUser);
    }

    // Read All Users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    // Read User By Id
    public Optional<UserResponseDTO> getUserById(String id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    // Update User
    public Optional<UserResponseDTO> updateUser(String id, UserInputDTO dto) {
        return userRepository.findById(id).map(existingUser -> {
            List<BooksModel> books = booksRepository.findAllById(dto.getBookIds());
            if (books.size() != dto.getBookIds().size()) {
                throw new IllegalArgumentException("One or more Book IDs are invalid");
            }

            existingUser.setFirstName(dto.getFirstName());
            existingUser.setLastName(dto.getLastName());
            existingUser.setEmail(dto.getEmail());
            existingUser.setPassword(dto.getPassword());
            existingUser.setContactNumber(dto.getContactNumber());
            existingUser.setRole(dto.getRole());
            existingUser.setStatus(dto.getStatus());
            existingUser.setBooks(books);
            existingUser.setUpdatedAt(new Date());

            UserModel updatedUser = userRepository.save(existingUser);
            return convertToDTO(updatedUser);
        });
    }

    // Delete User
    public void deleteUser(String id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with ID: " + id);
        }
        userRepository.deleteById(id);
    }
}
