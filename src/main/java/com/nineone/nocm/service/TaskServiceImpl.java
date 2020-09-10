package com.nineone.nocm.service;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.nineone.nocm.domain.Task;
import com.nineone.nocm.repository.TaskRepository;
import com.nineone.nocm.util.DateUtil;

@Service
public class TaskServiceImpl implements TaskService {
	@Autowired
	private TaskRepository taskRepository; 
	
	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public boolean insertTask(Task task) {
		taskRepository.updateTaskPositionByInsert(task);
		return (taskRepository.insertTask(task) > 0) ? true : false;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public boolean deleteTask(Task task) {
		taskRepository.updateTaskPositionByDelete(task);
		return (taskRepository.deleteTask(task.getId()) > 0 )? true : false;
	}

	@Override
	public boolean updateTaskContents(Task task) {
		Date edit_date = DateUtil.makeDate();
		task.setEdit_date(edit_date);
		return (taskRepository.updateTaskContents(task) > 0 )? true : false;
	}

	@Override
	@Transactional(isolation = Isolation.REPEATABLE_READ)
	public boolean updateTaskPosition(Map<String, Object> map) {
		if(map.get("tasklistNewId")==null) {
			boolean isUp = (int)map.get("taskNewIndex") < (int)map.get("taskOldIndex") ? true : false;
			map.put("isUp", isUp);
			taskRepository.moveTaskPosition(map);
			return  (taskRepository.updateTaskPosition(map)> 0)? true : false;
		}
		else {
			taskRepository.moveTaskPositionByDelete(map);
			taskRepository.moveTaskPositionByinsert(map);
			return (taskRepository.updateTaskPosition(map) > 0)? true : false;
		}
	}

	@Override
	public boolean updateTaskContent(Task task) {
		return (taskRepository.updateTaskContent(task)>0)?true:false;
	}

	@Override
	public boolean updateTaskTitle(Task task) {
		return (taskRepository.updateTaskTitle(task)>0)?true:false;
	}

	@Override
	@Transactional
	public boolean updateTaskDate(Task task) {
		taskRepository.updateTaskStartDate(task);
		return (taskRepository.updateTaskEndDate(task)>0)?true:false;
	}

	@Override
	public boolean updateTaskColor(Task task) {
		return (taskRepository.updateTaskColor(task)>0)?true:false;
	}

	@Override
	public boolean updateTaskProgRate(Task task) {
		return (taskRepository.updateTaskProgRate(task)>0)?true:false;
	}

	@Override
	public boolean updateTaskImportance(Task task) {
		return (taskRepository.updateTaskImportance(task)>0)?true:false;
	}

	@Override
	public boolean updateTaskAssignee(Task task) {
		return (taskRepository.updateTaskAssignee(task)>0)?true:false;
	}
}