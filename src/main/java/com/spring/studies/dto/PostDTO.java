package com.spring.studies.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.studies.domain.Post;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Document
public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private LocalDateTime date;
	
	private String title;
	
	private String body;
	
	private AuthorDTO author;
	
	public PostDTO(Post model) {
		id = model.getId();
		date = model.getDate();
		title = model.getTitle();
		body = model.getBody();
		author = model.getAuthor();
	}	
	
}
