package com.jdbc.controller;

import java.text.ParseException;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jdbc.dto.CreateTaskDto;
import com.jdbc.dto.ErrorResponseDTO;
import com.jdbc.dto.TaskResponseDTO;
import com.jdbc.dto.UpdateTaskDTO;
import com.jdbc.entity.Task;
import com.jdbc.service.NoteService;
import com.jdbc.service.TaskService;

@RestController
@RequestMapping("/tasks")
public class TaskController {

	private final TaskService service;
	private final NoteService service1;
	private ModelMapper mapper=new ModelMapper();
	
	
	@Autowired
	public TaskController(TaskService service,NoteService service1)
	{
	   this.service=service;	
	   this.service1=service1;
	}
	
	@GetMapping("")
	public ResponseEntity<List<Task>> getTasks()
	{
		var tasks=service.getAllTasks();
		return ResponseEntity.ok(tasks);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Task> findById(@PathVariable int id)
	{
		var task=service.getTaskById(id);
		var notes=service1.getNotesForTask(id);
		if(task==null)
		{
		    return ResponseEntity.notFound().build();
		}
//		task.setNotes(notes);
        var response=mapper.map(task,TaskResponseDTO.class);
        response.setNotes(notes);
		return ResponseEntity.ok(task);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Task> addTask(@RequestBody CreateTaskDto body)throws ParseException
	{
		Task task=service.addTask(body.getTitle(), body.getDescription(), body.getDeadline());
		return ResponseEntity.ok(task);
	}
	
	
	@PatchMapping("/update/{id}")
	public ResponseEntity<Task> updateTask(@PathVariable Integer id,@RequestBody UpdateTaskDTO dto) throws ParseException
	{
		var task=service.updateTask(id, dto.getDescription(), dto.getDeadline(), dto.getCompleted());
		if(task==null)
		{
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(task);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleErrors(Exception e)
    {
    	if(e instanceof ParseException)
    	{
    		return ResponseEntity.badRequest().body(new ErrorResponseDTO("Invalid Date Format"));
    	}
    	e.printStackTrace();
    	return ResponseEntity.badRequest().body(new ErrorResponseDTO("Server Error"));
    }
}
