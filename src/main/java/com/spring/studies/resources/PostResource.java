
package com.spring.studies.resources;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.studies.domain.Post;
import com.spring.studies.dto.PostDTO;
import com.spring.studies.resources.util.URL;
import com.spring.studies.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	PostService service;

	@GetMapping
	public ResponseEntity<List<PostDTO>> findAll() {
		List<PostDTO> posts = service.findAll();
		return ResponseEntity.ok(posts);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<PostDTO> findById(@PathVariable String id) {
		Post post = service.findById(id);
		PostDTO postDTO = service.fromModel(post);
		return ResponseEntity.ok(postDTO);
	}

	@GetMapping(value = "/titlesearch")
	public ResponseEntity<List<PostDTO>> findByTitle(
			@RequestParam(value = "text", defaultValue = StringUtils.EMPTY) String text) {
		text = URL.decodeParam(text);
		List<PostDTO> posts = service.findByTitle(text);
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping(value = "/fullsearch")
	public ResponseEntity<List<PostDTO>> fullSearch(
			@RequestParam(value = "text", defaultValue = StringUtils.EMPTY) String text,
			@RequestParam(value = "minDate", defaultValue = StringUtils.EMPTY) String minDate,
			@RequestParam(value = "maxDate", defaultValue = StringUtils.EMPTY) String maxDate) {
		text = URL.decodeParam(text);
		LocalDateTime min = URL.convertStringToLocalDateTime(minDate);
		LocalDateTime max = URL.convertStringToLocalDateTime(maxDate);
		
		List<PostDTO> posts = service.fullSearch(text, min, max);
		return ResponseEntity.ok().body(posts);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody PostDTO objDto, @PathVariable String id) {
		Post obj = service.fromDTO(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
}
