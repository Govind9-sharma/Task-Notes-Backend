package com.jdbc.dto;

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
public class CreateNoteResponseDTO {
	
	private Integer taskId;
	private Note note;
}
