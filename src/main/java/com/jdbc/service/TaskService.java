package com.jdbc.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jdbc.dto.ErrorResponseDTO;
import com.jdbc.entity.Task;

@Service
public class TaskService
{
	private ArrayList<Task> tasks=new ArrayList<>();
    private int taskId=1;
    private SimpleDateFormat format=new SimpleDateFormat("yyyy-mm-dd");
    
    public Task addTask(String title,String description,String deadLine) throws ParseException
    {
    	Task task=new Task();
    	task.setId(taskId++);
    	task.setDeadLine(format.parse(deadLine));
    	task.setDescription(description);
    	task.setTitle(title);
    	task.setCompleted(false);
    	tasks.add(task);
    	return task;
    }
    
    public ArrayList<Task> getAllTasks()
    {
    	return tasks;
    }
    
    public Task getTaskById(int id)
    {
        Stream<Task> stream=tasks.stream().filter((task)->task.getId()==id);
        Optional<Task> task=stream.findFirst();     
        Task temp=task.get();
        if(temp==null)
        {
        	return null;
        }
        return temp;
    }
    
    
    public Task updateTask(int id,String description,String deadLine,Boolean completed)throws ParseException
    {
    	Task task =getTaskById(id);
    	if(task==null)
    	{
             return null;
    	}
    	if(description!=null)
    	task.setDescription(description);
    	if(deadLine!=null)
    	task.setDeadLine(format.parse(deadLine));
    	if(completed!=null)
    	task.setCompleted(completed);
    	return task;
    }
}