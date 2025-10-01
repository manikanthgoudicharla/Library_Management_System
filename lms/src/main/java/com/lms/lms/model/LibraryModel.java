package com.lms.lms.model;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "library")
public class LibraryModel {

    @Id
    private String id;

    @Field(name = "library_name", targetType = FieldType.STRING)
    @Indexed(unique = true)
    private String name;

    @Field(name = "library_address", targetType = FieldType.STRING)
    private String address;

    @Field(name = "library_status", targetType = FieldType.STRING)
    private LibraryStatus status;

    @Field(name = "contact_number", targetType = FieldType.STRING)
    private String contactNumber;

    @Field(name = "library_email", targetType = FieldType.STRING)
    private String email;

    @Field(name = "total_capacity", targetType = FieldType.INT32)
    private Integer totalCapacity;

    @Field(name = "total_books", targetType = FieldType.INT32)
    private Integer totalBooks;

    @Field(name = "late_fee", targetType = FieldType.INT32)
    private Integer lateFee;

    @Field(name = "opening_time", targetType = FieldType.STRING)
    private String openingTime;

    @Field(name = "closing_time", targetType = FieldType.STRING)
    private String closingTime;

    @CreatedDate
    @Field(name = "created_at", targetType = FieldType.DATE_TIME)
    private Date createdAt;

    @LastModifiedDate
    @Field(name = "updated_at", targetType = FieldType.DATE_TIME)
    private Date updatedAt;
}
