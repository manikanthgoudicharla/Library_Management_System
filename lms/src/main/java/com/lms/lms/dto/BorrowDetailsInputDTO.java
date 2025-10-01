package com.lms.lms.dto;

import jakarta.validation.constraints.*;
import java.util.Date;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDetailsInputDTO{

    @NotBlank(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    private String userName;

    @NotBlank(message = "Book name is required")
    @Size(min = 2, max = 100, message = "Book name must be between 2 and 100 characters")
    private String bookName;

    @NotBlank(message = "Borrow Count is required ")
    private int borrowCount;

    @NotBlank(message = "Library name is required")
    @Size(min = 2, max = 100, message = "Library name must be between 2 and 100 characters")
    private String libraryName;

    @Min(value = 0, message = "Late fee cannot be negative")
    @Max(value = 5000, message = "Late fee cannot exceed 5000 INR")
    private int lateFee;

    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date cannot be in the future")
    private Date issueDate;

    @FutureOrPresent(message = "Return date cannot be in the past")
    private Date returnDate;
}
