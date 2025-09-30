package com.lms.lms.dto;

import com.lms.lms.model.LibraryStatus;
import lombok.Data;
import java.util.Date;

@Data
public class LibraryResponseDTO {

    private String id;
    private String name;
    private String address;
    private LibraryStatus status;
    private String contactNumber;
    private String email;
    private int totalCapacity;
    private int totalBooks;
    private String openingTime;
    private String closingTime;
    private Date createdAt;
    private Date updatedAt;
}
