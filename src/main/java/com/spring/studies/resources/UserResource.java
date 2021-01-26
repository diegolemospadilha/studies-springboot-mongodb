
package com.spring.studies.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
