package com.spring.studies.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.studies.domain.User;
import com.spring.studies.dto.UserDTO;
import com.spring.studies.repository.UserRepository;
import com.spring.studies.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> users = repository.findAll();
		return users.stream().map(model -> new UserDTO(model)).collect(Collectors.toList());
	}
	
	public User findById(String id) {
		Optional<User> model = repository.findById(id);
		return model.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}
}
