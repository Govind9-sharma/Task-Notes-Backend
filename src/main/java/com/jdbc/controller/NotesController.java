package com.jdbc.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jdbc.dto.CreateNoteDTO;
import com.jdbc.dto.CreateNoteResponseDTO;
import com.jdbc.entity.Note;
import com.jdbc.service.NoteService;

@RestController
@RequestMapping("tasks/{taskId}/notes")
public class NotesController {
	
	@Autowired
	private NoteService service;
	
	@GetMapping("")
	public ResponseEntity<List<Note>> getNotes(@PathVariable Integer taskId)
	{
		var notes=service.getNotesForTask(taskId);
		return ResponseEntity.ok(notes);
	}
	
	@PostMapping("/create")
	public ResponseEntity<CreateNoteResponseDTO> createNote(@PathVariable Integer taskId,@RequestBody CreateNoteDTO note)
	{
          var response=service.addNoteForTask(taskId, note.getTitle(), note.getBody());
          return ResponseEntity.ok(new CreateNoteResponseDTO(taskId,response));
	}
	
//	@GetMapping()
//	public ResponseEntiy<> 
}