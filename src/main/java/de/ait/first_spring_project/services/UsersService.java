package de.ait.first_spring_project.services;

import de.ait.first_spring_project.dto.tasksdto.TasksDto;
import de.ait.first_spring_project.dto.usersdto.NewUserDto;
import de.ait.first_spring_project.dto.usersdto.UpdateUserDto;
import de.ait.first_spring_project.dto.usersdto.UserDto;
import de.ait.first_spring_project.dto.usersdto.UsersDto;

public interface UsersService {
    UserDto addUser(NewUserDto newUser);

    UsersDto getAllUsers();

    UserDto getUser(Long userId);

    UserDto updateUser(Long userId, UpdateUserDto updateUser);

    TasksDto getAllTasksOfUser(Long userId);

    UserDto deleteUser(Long userId);
}
