package com.lms.lms.dto;

import lombok.Data;
import com.lms.lms.model.BookStatus;
import java.util.Date;

@Data
public class BooksResponseDTO {

    private String id;
    private String title;
    private String author;
    private String category;
    private Integer publicationYear;
    private BookStatus status;
    private int bookCount;

    private String libraryId;      // Reference ID of the library
    private String libraryName;    // Human-readable name of the library

    private Date createdAt;
    private Date updatedAt;
}
