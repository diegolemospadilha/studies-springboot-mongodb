package com.spring.studies.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spring.studies.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	
	public List<Post> findByTitleContainingIgnoreCase(String title);
	
	@Query("{ 'title': { $regex: ?0, $options: 'i' } }")
	public List<Post> findByTitle(String title);
	
	@Query("{ $and: [ { date: { $gte: ?1}  }, { date: { $lte: ?2} } , { $or: [ { 'title': { $regex: ?0, $options: 'i' } }, { 'body': { $regex: ?0, $options: 'i' } } , { 'comments.text': { $regex: ?0, $options: 'i' } } ] } ] }")
	public List<Post> fullSearch(String text, LocalDateTime minDate,  LocalDateTime maxDate);

}
