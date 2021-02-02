package com.spring.studies.services;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
    private ModelMapper modelMapper;

	public List<PostDTO> findAll() {
		List<Post> posts = repository.findAll();
		return posts.stream().map(this:: fromModel).collect(Collectors.toList());
	}

	public Post findById(String id) {
		Optional<Post> model = repository.findById(id);
		return model.orElseThrow(() -> new ObjectNotFoundException(
				"Post não encontrado! Id: " + id + ", Tipo: " + Post.class.getName()));
	}
	
	public List<PostDTO> findByTitle(String title) {
		List<Post> posts = repository.findByTitleContainingIgnoreCase(title);
		return posts.stream().map(this:: fromModel).collect(Collectors.toList());
	}
	
	public List<PostDTO> fullSearch(String text, LocalDateTime minDate,  LocalDateTime maxDate){
		maxDate = maxDate.plusDays(1);
		List<Post> posts = repository.fullSearch(text, minDate, maxDate);
		return posts.stream().map(this:: fromModel).collect(Collectors.toList());
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
		return this.modelMapper.map(objDTO, Post.class);
	}
	
	public PostDTO fromModel(Post model) {
		return this.modelMapper.map(model, PostDTO.class);
	}
}
