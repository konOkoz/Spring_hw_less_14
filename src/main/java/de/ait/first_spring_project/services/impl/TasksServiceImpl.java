package de.ait.first_spring_project.services.impl;

import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.dto.tasksdto.TaskDto;
import de.ait.first_spring_project.dto.tasksdto.TasksDto;
import de.ait.first_spring_project.exceptions.IncorrectUserIdException;
import de.ait.first_spring_project.models.Task;
import de.ait.first_spring_project.models.User;
import de.ait.first_spring_project.repositories.TasksRepository;
import de.ait.first_spring_project.repositories.UsersRepository;
import de.ait.first_spring_project.services.TasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import static de.ait.first_spring_project.dto.tasksdto.TaskDto.from;

@RequiredArgsConstructor
@Service
public class TasksServiceImpl implements TasksService {

    private final UsersRepository usersRepository;
    private final TasksRepository tasksRepository;
    @Override
    public TaskDto addTask(Long userId, NewTaskDto newTask) {
        User user =  usersRepository.findById(userId)
                .orElseThrow(() ->
                        new IncorrectUserIdException(newTask.getAboutUserId()));

        Task task = Task.builder()
                .description(newTask.getDescription())
                .title(newTask.getTitle())
                .startDate(LocalDate.parse(newTask.getStartDate()))
                .finishDate(LocalDate.parse(newTask.getFinishDate()))
                .executor(user)
                .build();

        tasksRepository.save(task);

        return from(task);

    }


}
