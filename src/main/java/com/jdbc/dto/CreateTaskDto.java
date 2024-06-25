package com.jdbc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

//DTO Stands for The Data Transfer Object.

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CreateTaskDto {
	String title;
	String description;
	String deadline;
}
