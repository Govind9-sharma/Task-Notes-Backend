package com.jdbc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Service;

import com.jdbc.entity.Note;
import com.jdbc.entity.Task;

@Service
public class NoteService {
	
	private TaskService service;
	private HashMap<Integer,TaskNotesHolder> taskNoteHolder=new HashMap<>();
	
	public NoteService(TaskService service)
	{
		this.service=service;
	}
	
	class TaskNotesHolder{
		protected int noteId;
		protected ArrayList<Note> notes=new ArrayList<>();
	}
	
	public List<Note> getNotesForTask(int taskId)
	{
		Task task=service.getTaskById(taskId);
		if(task==null)
		{
			return null;
		}
		
		if(taskNoteHolder.get(taskId) ==null)
		{
			taskNoteHolder.put(taskId,new TaskNotesHolder());
		}
		
		return taskNoteHolder.get(taskId).notes;
	}
	
	public Note addNoteForTask(int taskId,String title,String body)
	{
		Task task=service.getTaskById(taskId);
		
		if(task==null)
		{
			return null;
		}
		if(taskNoteHolder.get(taskId)==null)
		{
			taskNoteHolder.put(taskId, new TaskNotesHolder());
		}
		TaskNotesHolder taskNotesHolder=taskNoteHolder.get(taskId);
		Note note=new Note();
		note.setId(taskNotesHolder.noteId);
		note.setTitle(title);
		note.setBody(body);
		taskNotesHolder.notes.add(note);
		taskNotesHolder.noteId++;
		return note;
	}
}
