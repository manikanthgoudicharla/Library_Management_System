package com.lms.lms.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import com.lms.lms.model.BookStatus;

@Data
public class BooksInputDTO {

    @NotBlank(message = "Title is required")
    @Size(max = 200, message = "Title cannot exceed 200 characters")
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 100, message = "Author cannot exceed 100 characters")
    private String author;

    @NotBlank(message = "Category is required")
    @Size(max = 100, message = "Category cannot exceed 100 characters")
    private String category;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 9999, message = "Publication year must be a valid year")
    private Integer publicationYear;

    @NotNull(message = "Status is required")
    private BookStatus status;

    @NotBlank(message = "Library ID is required")
    private String libraryId;
}
