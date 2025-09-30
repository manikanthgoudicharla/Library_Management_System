package com.lms.lms.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lms.lms.model.LibraryModel;

@Repository
public interface LibraryRepository extends MongoRepository<LibraryModel, String> {
    List<LibraryModel> findByStatus(String status);
}

