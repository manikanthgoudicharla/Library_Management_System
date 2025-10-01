package com.lms.lms.repository;

// Repository
import org.springframework.stereotype.Repository;

// MongoRepo
import org.springframework.data.mongodb.repository.MongoRepository;

// Borrow Model
import com.lms.lms.model.BorrowDetailsModel;


//utils
import java.util.Optional;


@Repository
public interface BorrowDetailsRepository extends MongoRepository<BorrowDetailsModel,String> {
     Optional<BorrowDetailsModel> findTopByUserNameAndBookNameOrderByIssueDateDesc(String userName, String bookName);

}
