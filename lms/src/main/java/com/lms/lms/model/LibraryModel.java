package com.lms.lms.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import jakarta.validation.constraints.*;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "library")
public class LibraryModel {

    @Id
    private String id;

    @NotBlank(message = "Library name is required")
    @Size(max = 100, message = "Library name cannot exceed 100 characters")
    @Field(name = "library_name", targetType = FieldType.STRING)
    @Indexed(unique = true)
    private String name;

    @NotBlank(message = "Library address is required")
    @Size(max = 200, message = "Library address cannot exceed 200 characters")
    @Field(name = "library_address", targetType = FieldType.STRING)
    private String address;

    @NotNull(message = "Library status is required")
    @Field(name = "library_status", targetType = FieldType.STRING)
    private LibraryStatus status;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^\\+?[0-9\\s\\-]{10,20}$",
        message = "Invalid contact number. It must contain 10 to 20 digits and can include spaces or hyphens."
    )
    @Field(name = "contact_number", targetType = FieldType.STRING)
    private String contactNumber;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email cannot exceed 100 characters")
    @Field(name = "library_email", targetType = FieldType.STRING)
    private String email;

    @NotNull(message = "Total capacity is required")
    @Positive(message = "Total capacity must be a positive number")
    @Field(name = "total_capacity", targetType = FieldType.INT32)
    private int totalCapacity;

    @NotNull(message = "Total books is required")
    @Positive(message = "Total books must be a positive number")
    @Field(name = "total_books", targetType = FieldType.INT32)
    private int totalBooks;

    @NotBlank(message = "Opening time is required")
    @Size(max = 10, message = "Opening time cannot exceed 10 characters")
    @Field(name = "opening_time", targetType = FieldType.STRING)
    private String openingTime;

    @NotBlank(message = "Closing time is required")
    @Size(max = 10, message = "Closing time cannot exceed 10 characters")
    @Field(name = "closing_time", targetType = FieldType.STRING)
    private String closingTime;

    @CreatedDate
    @NotNull(message = "Created date is required")
    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    @LastModifiedDate
    @NotNull(message = "Updated date is required")
    @Field(name = "updated_at", targetType = FieldType.DATE_TIME)
    private Date updatedAt;

}
