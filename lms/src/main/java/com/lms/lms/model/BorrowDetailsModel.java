package com.lms.lms.model;

// Lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Document,Field,FieldType,dbref from data.mogodb.core.mapping
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

// Id,createdat,updatedat from data.annotation
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// jakarta.validation.constraints
import jakarta.validation.constraints.*;

// Date
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "borrowdetails")
public class BorrowDetailsModel {

    @Id
    private String id;

    @NotBlank(message = "User name is required")
    @Size(min = 3, max = 50, message = "User name must be between 3 and 50 characters")
    @Field(name = "userName", targetType = FieldType.STRING)
    private String userName;

    @NotBlank(message = "Book name is required")
    @Size(min = 2, max = 100, message = "Book name must be between 2 and 100 characters")
    @Field(name = "bookName", targetType = FieldType.STRING)
    private String bookName;

    @NotBlank(message = "Library name is required")
    @Size(min = 2, max = 100, message = "Library name must be between 2 and 100 characters")
    @Field(name = "libraryName", targetType = FieldType.STRING)
    private String libraryName;

    @Min(value = 0, message = "Late fee cannot be negative")
    @Max(value = 5000, message = "Late fee cannot exceed 5000 INR")
    @Field(name = "lateFee", targetType = FieldType.INT32)
    private int lateFee;

    @Field(name="borrowCount" , targetType = FieldType.INT32)
    private int borrowCount;

    @NotNull(message = "Issue date is required")
    @PastOrPresent(message = "Issue date cannot be in the future")
    @CreatedDate
    @Field(name = "issueDate", targetType = FieldType.DATE_TIME)
    private Date issueDate;

    @FutureOrPresent(message = "Return date cannot be in the past")
    @LastModifiedDate
    @Field(name = "returnDate", targetType = FieldType.DATE_TIME)
    private Date returnDate;
}
