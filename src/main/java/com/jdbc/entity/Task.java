package com.jdbc.entity;

import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
public class Task {

	private int id;
	private String title;
	private String description;
	private Date deadLine;
	private boolean completed;
//	private List<Note> notes;
}
