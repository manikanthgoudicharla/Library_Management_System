package com.lms.lms.repository;

// Repository
import org.springframework.stereotype.Repository;

// Mongorepository
import org.springframework.data.mongodb.repository.MongoRepository;

// Model
import com.lms.lms.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel,String> {
    
}
