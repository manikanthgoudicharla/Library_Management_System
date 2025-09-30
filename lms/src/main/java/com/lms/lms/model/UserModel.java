package com.lms.lms.model;

// Lombok
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// MongoDB annotations
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;

// Spring Data annotations
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// Jakarta validations
import jakarta.validation.constraints.*;

// Utils
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "users")
public class UserModel {

    @Id
    private String id;

    @NotBlank(message = "First name is required")
    @Size(max = 50, message = "First name must not exceed 50 characters")
    @Field(name = "firstName", targetType = FieldType.STRING)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 50, message = "Last name must not exceed 50 characters")
    @Field(name = "lastName", targetType = FieldType.STRING)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    @Field(name = "email", targetType = FieldType.STRING)
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters")
    @Pattern(
        regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$",
        message = "Password must contain at least one letter, one number, and one special character"
    )
    @Field(name = "password", targetType = FieldType.STRING)
    private String password;

    @NotBlank(message = "Contact number is required")
    @Pattern(
        regexp = "^\\+?[0-9\\s\\-]{10,20}$",
        message = "Contact number must be valid and contain 10-20 digits"
    )
    @Field(name = "contactNumber", targetType = FieldType.STRING)
    private String contactNumber;

    @NotNull(message = "Role is required")
    @Field(name = "role", targetType = FieldType.STRING)
    private UserRole role;

    @NotNull(message = "Status is required")
    @Field(name = "status", targetType = FieldType.STRING)
    private UserStatus status;

    @DBRef
    @Field(name = "books")
    private List<BooksModel> books;

    @CreatedDate
    @Field(name = "createdAt", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    @LastModifiedDate
    @Field(name = "updatedAt", targetType = FieldType.DATE_TIME)
    private Date updatedAt;
}
