package com.lms.lms.repository;

// Repository annotation
import org.springframework.stereotype.Repository;

// MongoRepository import
import org.springframework.data.mongodb.repository.MongoRepository;

// BooksModel import
import com.lms.lms.model.BooksModel;

// utila
import java.util.List;

@Repository
public interface BooksRepository extends MongoRepository<BooksModel, String> {
    List<BooksModel> findByLibraryId(String libraryId);
}

