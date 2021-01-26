package com.spring.studies.dto;

import java.io.Serializable;

import com.spring.studies.domain.User;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter @Setter
public class UserDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String name;
	private String email;
	
	public UserDTO(User model) {
		id = model.getId();
		name = model.getName();
		email = model.getEmail();
	}
	
	
}
