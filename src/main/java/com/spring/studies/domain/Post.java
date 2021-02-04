package com.spring.studies.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.spring.studies.dto.CommentDTO;

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
public class Post implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	private String id;
	
	private LocalDateTime date;
	
	private String title;
	
	private String body;
	
	private List<CommentDTO> comments = new ArrayList<CommentDTO>();

	public Post(String id, LocalDateTime date, String title, String body) {
		super();
		this.id = id;
		this.date = date;
		this.title = title;
		this.body = body;
	}
	
}
