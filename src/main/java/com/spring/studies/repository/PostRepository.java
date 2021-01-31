package com.spring.studies.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.spring.studies.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
