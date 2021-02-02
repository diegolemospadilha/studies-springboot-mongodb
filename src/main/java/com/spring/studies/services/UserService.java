package com.spring.studies.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
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
	
	@Autowired
    private ModelMapper modelMapper;

	public List<UserDTO> findAll() {
		List<User> users = repository.findAll();
		return users.stream().map(this::fromModel).collect(Collectors.toList());
	}

	public User findById(String id) {
		Optional<User> model = repository.findById(id);
		return model.orElseThrow(() -> new ObjectNotFoundException(
				"Usuário não encontrado! Id: " + id + ", Tipo: " + User.class.getName()));
	}

	public User insert(User obj) {
		return repository.insert(obj);

	}

	public void delete(String id) {
		findById(id);
		repository.deleteById(id);
	}
	
	public void update(User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj);
		repository.save(newObj);
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDTO) {
		return this.modelMapper.map(objDTO, User.class);
	}
	
	public UserDTO fromModel(User model) {
		return this.modelMapper.map(model, UserDTO.class);
	}
}
