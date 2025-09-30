package com.lms.lms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.lms.lms.model.LibraryModel;

@Repository
public interface LibraryRepository extends MongoRepository<LibraryModel,Object> {
    
}
