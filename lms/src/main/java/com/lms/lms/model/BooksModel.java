package com.lms.lms.model;
// Lomok

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

// Document,Field,FieldType,dbref from data.mogodb.core.mapping
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.DBRef;

// Id,createdat,updatedat from data.annotation
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

// Indexed from data.mongodb.core.index
import org.springframework.data.mongodb.core.index.Indexed;

// jakarta.validation.constraints
import jakarta.validation.constraints.*;

// Date
import java.util.Date;

// LibraryModel
import com.lms.lms.model.LibraryModel;

import org.springframework.beans.factory.annotation.Autowired;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class BooksModel {
    @Autowired
    private LibraryModel libraryModel;

    @Id
    private String id;

    @NotBlank(message = "Title is required")
    @Size(max = 200)
    @Indexed(unique = true)
    private String title;

    @NotBlank(message = "Author is required")
    @Size(max = 100)
    private String author;

    @NotBlank(message = "Category is required")
    @Size(max = 100)
    private String category;

    @NotNull(message = "Publication year is required")
    @Min(value = 1000, message = "Publication year must be a valid year")
    @Max(value = 9999, message = "Publication year must be a valid year")
    private Integer publicationYear;

    @NotNull(message = "Status is required")
    private BookStatus status;

    @DBRef
    @NotNull(message = "Library reference is required")
    private LibraryModel library;

    @CreatedDate
    @Field(targetType = FieldType.DATE_TIME)
    private Date createdAt;

    @LastModifiedDate
    @Field(targetType = FieldType.DATE_TIME)
    private Date updatedAt;

}





