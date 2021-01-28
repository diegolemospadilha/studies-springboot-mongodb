
package com.spring.studies.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.spring.studies.domain.User;
import com.spring.studies.dto.UserDTO;
import com.spring.studies.services.UserService;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	UserService service;

	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll() {
		List<UserDTO> users = service.findAll();
		return ResponseEntity.ok(users);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id) {
		User user = service.findById(id);
		return ResponseEntity.ok(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<UserDTO> insert(@RequestBody UserDTO user) {
		User obj = service.fromDTO(user);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
}
