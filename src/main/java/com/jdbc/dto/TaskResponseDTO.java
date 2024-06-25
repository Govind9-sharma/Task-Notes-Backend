package com.jdbc.dto;

import java.util.Date;
import java.util.List;

import com.jdbc.entity.Note;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class TaskResponseDTO {
	private int id;
	private String title;
	private String description;
	private Date deadLine;
	private boolean completed;
	
	private List<Note> notes;
}
