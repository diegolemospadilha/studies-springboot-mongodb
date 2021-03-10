package com.spring.studies.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

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
public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;

	private LocalDateTime lastUpdateDate;

	private String title;

	private String body;

	private AuthorDTO author;
}
