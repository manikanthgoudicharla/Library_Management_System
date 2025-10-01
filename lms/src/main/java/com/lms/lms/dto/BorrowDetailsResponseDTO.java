package com.lms.lms.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BorrowDetailsResponseDTO {

    private String id;
    private String userName;
    private String bookName;
    private String libraryName;
    private int borrowCount;
    private int lateFee;
    private Date issueDate;
    private Date returnDate;
}
