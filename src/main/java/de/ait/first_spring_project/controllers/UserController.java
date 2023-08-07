package de.ait.first_spring_project.controllers;


import de.ait.first_spring_project.controllers.api.UsersApi;
import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.dto.tasksdto.TaskDto;
import de.ait.first_spring_project.dto.tasksdto.TasksDto;
import de.ait.first_spring_project.dto.usersdto.NewUserDto;
import de.ait.first_spring_project.dto.usersdto.UpdateUserDto;
import de.ait.first_spring_project.dto.usersdto.UserDto;
import de.ait.first_spring_project.dto.usersdto.UsersDto;
import de.ait.first_spring_project.services.TasksService;
import de.ait.first_spring_project.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController implements UsersApi {

    private final UsersService usersService;
    private final TasksService tasksService;

    @Override
    public ResponseEntity<UserDto> addUser(NewUserDto newUser) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usersService.addUser(newUser));

    }

    @Override
    public ResponseEntity<UserDto> getUser(Long userId) {
        return ResponseEntity
                .ok(usersService.getUser(userId));
    }

    @Override
    public ResponseEntity<UsersDto> getAllUsers() {
        return ResponseEntity
                .ok(usersService.getAllUsers());
    }

    @Override
    public ResponseEntity<UserDto> updateUser(Long userId, UpdateUserDto updateUser) {
        return ResponseEntity
                .ok(usersService.updateUser(userId, updateUser));
    }


    @Override
    public ResponseEntity<TaskDto> addTask(Long userId, NewTaskDto newTask) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(tasksService.addTask(userId, newTask));
    }

    @Override
    public ResponseEntity<TasksDto> getAllTasksOfUser(Long userId) {
        return ResponseEntity
                .ok(usersService.getAllTasksOfUser(userId));
    }
}
