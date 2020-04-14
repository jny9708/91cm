package com.nineone.nocm.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nineone.nocm.domain.Task;
import com.nineone.nocm.repository.TaskRepository;
import com.nineone.nocm.util.DateUtil;

@Service
public class TaskServiceImpl implements TaskService{
	
	@Autowired
	private TaskRepository taskRepository; 
	
	@Override
	public boolean insertTask(Task task) {
		return (taskRepository.insertTask(task) > 0) ? true : false;
	}

	@Override
	public boolean deleteTask(int id) {
		return (taskRepository.deleteTask(id) > 0 )? true : false;
	}

	@Override
	public boolean updateTaskContent(Task task) {
		Date edit_date = DateUtil.makeDate();
		task.setEdit_date(edit_date);
		return (taskRepository.updateTaskContent(task) > 0 )? true : false;
	}

	@Override
	public boolean updateTaskPosition(Task task) {
		return (taskRepository.updateTaskPosition(task) > 0 )? true : false;
	}

}