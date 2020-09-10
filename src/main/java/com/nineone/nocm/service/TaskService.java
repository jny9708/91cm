package com.nineone.nocm.service;

import java.util.Map;

import com.nineone.nocm.domain.Task;

public interface TaskService {
	boolean insertTask(Task task);
	boolean deleteTask(Task task);
	boolean updateTaskContents(Task task);
	boolean updateTaskPosition(Map<String,Object> map);
	boolean updateTaskContent(Task task);
	boolean updateTaskTitle(Task task);
	boolean updateTaskDate(Task task);
	boolean updateTaskColor(Task task);
	boolean updateTaskProgRate(Task task);
	boolean updateTaskImportance(Task task);
	boolean updateTaskAssignee(Task task);
}
