package com.spring.studies.repository;

import org.springframework.stereotype.Repository;

import com.spring.studies.domain.User;

import org.springframework.data.mongodb.repository.MongoRepository;

@Repository
public interface UserRepository extends MongoRepository<User, String>{

}
