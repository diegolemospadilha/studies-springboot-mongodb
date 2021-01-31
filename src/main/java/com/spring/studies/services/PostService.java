package com.spring.studies.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.studies.domain.Post;
import com.spring.studies.dto.PostDTO;
import com.spring.studies.repository.PostRepository;
import com.spring.studies.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	PostRepository repository;

	public List<PostDTO> findAll() {
		List<Post> posts = repository.findAll();
		return posts.stream().map(model -> new PostDTO(model)).collect(Collectors.toList());
	}

	public Post findById(String id) {
		Optional<Post> model = repository.findById(id);
		return model.orElseThrow(() -> new ObjectNotFoundException(
				"Post não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}

	public Post insert(Post obj) {
		return repository.insert(obj);

	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}

	public void update(Post obj) {
		Post newObj = findById(obj.getId());
		updateData(newObj, obj);
		repository.save(newObj);
	}

	private void updateData(Post newObj, Post obj) {
		newObj.setTitle(obj.getTitle());
		newObj.setBody(obj.getBody());
		newObj.setDate(obj.getDate());

	}

	public Post fromDTO(PostDTO objDTO) {
		return new Post(objDTO.getId(), objDTO.getDate(), objDTO.getTitle(), objDTO.getBody(), objDTO.getAuthor());
	}
}
