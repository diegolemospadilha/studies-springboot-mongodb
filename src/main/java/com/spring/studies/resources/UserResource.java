
package com.spring.studies.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.studies.domain.User;

@RestController
@RequestMapping(value = "/users")
public class UserResource {
	
	@GetMapping
	public ResponseEntity<List<User>> findAll(){
		User user1 = new User("1", "Tony Kroos", "tony@email.com");
		User user2 = new User("2", "Maria Kroos", "tony@email.com");
		User user3 = new User("3", "Tonya Kroos", "tony@email.com");
		User user4 = new User("id", "name", "email");
		
		List<User> users = Arrays.asList(user1, user2, user3);
		
		return ResponseEntity.ok(users);
	}
}
