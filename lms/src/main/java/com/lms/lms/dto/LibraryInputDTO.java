package com.lms.lms.dto;

import jakarta.validation.constraints.*;
import com.lms.lms.model.LibraryStatus;
import lombok.Data;

@Data
public class LibraryInputDTO {

    @NotBlank(message = "Library name is required")
    @Size(max = 100)
    private String name;

    @NotBlank(message = "Library address is required")
    @Size(max = 200)
    private String address;

    @NotNull(message = "Library status is required")
    private LibraryStatus status;

    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "^\\+?[0-9\\s\\-]{10,20}$", message = "Invalid contact number")
    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100)
    private String email;

    @NotNull(message="Late fee is Required")
    @Positive(message="Late fee should be a positive number")
    private Integer lateFee;

    @NotNull(message = "Total capacity is required")
    @Positive(message = "Total capacity must be positive")
    private Integer totalCapacity;

    @NotNull(message = "Total books is required")
    @Positive(message = "Total books must be positive")
    private Integer totalBooks;

    @NotBlank(message = "Opening time is required")
    @Size(max = 10)
    private String openingTime;

    @NotBlank(message = "Closing time is required")
    @Size(max = 10)
    private String closingTime;
}
