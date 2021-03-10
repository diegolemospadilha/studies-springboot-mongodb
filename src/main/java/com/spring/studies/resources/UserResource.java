
package com.spring.studies.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.studies.domain.Post;
import com.spring.studies.domain.User;
import com.spring.studies.dto.PostDTO;
import com.spring.studies.dto.UserDTO;
import com.spring.studies.services.PostService;
import com.spring.studies.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService service;
	
	@Autowired
	PostService postService;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = service.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		UserDTO userDTO = service.fromModel(user);
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO objDto) {
		User obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
		User obj = service.fromDTO(objDto);
		obj.setId(id);
		service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	/** METODOS REFERENTES A POSTS DOS USUÁRIOS **/
	
	
	@GetMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> findPostByUser(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok(user.getPosts());
	}
	
	@PostMapping(value = "/{id}/posts")
	public ResponseEntity<List<Post>> insertPostByUser(@PathVariable String id, @RequestBody PostDTO postDTO) {
		User user = service.findById(id);
		Post newPost = postService.fromDTO(postDTO);
		newPost = postService.insert(newPost);
		user.setId(id);
		user.getPosts().add(newPost);
		service.update(user);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}/posts/{idPost}").buildAndExpand(user.getId(), newPost.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{id}/posts/{idPost}")
	public ResponseEntity<List<Post>> updatePostByUser(@PathVariable String id, @PathVariable String idPost, @RequestBody PostDTO postDTO) {
		User user = service.findById(id);
		Post post = postService.fromDTO(postDTO);
		user.setId(id);
		post.setId(idPost);
		postService.update(post);
		return ResponseEntity.noContent().build();
	}
}
