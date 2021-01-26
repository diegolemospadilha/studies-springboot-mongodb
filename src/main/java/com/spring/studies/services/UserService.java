package com.spring.studies.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.studies.domain.User;
import com.spring.studies.dto.UserDTO;
import com.spring.studies.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository repository;
	
	public List<UserDTO> findAll(){
		List<User> users = repository.findAll();
		List<UserDTO> usersDTO = users.stream().map(model -> new UserDTO(model)).collect(Collectors.toList());
		return usersDTO;
	}
}
