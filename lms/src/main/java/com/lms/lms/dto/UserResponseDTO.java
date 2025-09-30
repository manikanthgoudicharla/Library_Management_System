package com.lms.lms.dto;

import lombok.Data;
import com.lms.lms.model.UserRole;
import com.lms.lms.model.UserStatus;

import java.util.Date;
import java.util.List;

@Data
public class UserResponseDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String email;

    private String contactNumber;

    private UserRole role;

    private UserStatus status;

    private List<String> borrowedBookTitles; 

    private Date createdAt;

    private Date updatedAt;
}
