package com.lms.lms.dto;

import lombok.Data;
import jakarta.validation.constraints.*;
import com.lms.lms.model.UserRole;
import com.lms.lms.model.UserStatus;

import java.util.List;

@Data
public class UserInputDTO {

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "Password must contain at least one letter, one number, and one special character"
    )
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^\\+?[0-9\\s\\-]{10,20}$",
        message = "Contact number must be valid and contain 10-20 digits"
    )
    private String contactNumber;

    @NotNull(message = "Role is required")
    private UserRole role;

    @NotNull(message = "Status is required")
    private UserStatus status;

    // List of borrowed book IDs
    private List<String> bookIds;
}
