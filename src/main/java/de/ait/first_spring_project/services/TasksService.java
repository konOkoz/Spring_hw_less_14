package de.ait.first_spring_project.services;

import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.dto.tasksdto.TaskDto;
import de.ait.first_spring_project.dto.tasksdto.TasksDto;


public interface TasksService {
    TaskDto addTask(Long userId, NewTaskDto newTask);

}
