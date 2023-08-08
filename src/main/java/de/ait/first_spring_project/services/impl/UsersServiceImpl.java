package de.ait.first_spring_project.services.impl;

import de.ait.first_spring_project.dto.tasksdto.TasksDto;
import de.ait.first_spring_project.dto.usersdto.UpdateUserDto;
import de.ait.first_spring_project.exceptions.ForbiddenOperationException;
import de.ait.first_spring_project.exceptions.NotFoundException;
import de.ait.first_spring_project.dto.usersdto.NewUserDto;
import de.ait.first_spring_project.dto.usersdto.UserDto;
import de.ait.first_spring_project.dto.usersdto.UsersDto;
import de.ait.first_spring_project.models.User;
import de.ait.first_spring_project.repositories.UsersRepository;
import de.ait.first_spring_project.services.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static de.ait.first_spring_project.dto.usersdto.UserDto.from;
import static de.ait.first_spring_project.dto.tasksdto.TaskDto.from;

@RequiredArgsConstructor
@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    @Override
    public UserDto addUser(NewUserDto newUser) {
        User user = User.builder()
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .role(User.Role.USER)
                .state(User.State.NOT_CONFIRMED)
                .build();
        usersRepository.save(user);

        return from(user);
    }

    @Override
    public UsersDto getAllUsers() {
        List<User> users = usersRepository.findAll();
        return UsersDto.builder()
                .users(from(users))
                .count(users.size())
                .build();
    }

    @Override
    public UserDto getUser(Long userId) {
        return from(getUserOrThrow(userId));
    }

    @Override
    public UserDto updateUser(Long userId, UpdateUserDto updateUser) {

        User user = getUserOrThrow(userId);

        if (updateUser.getNewRole().equals("ADMIN")) {
            throw new ForbiddenOperationException("role", "ADMIN");
        }

        if(updateUser.getNewState().equals("BANNED")){
            throw new ForbiddenOperationException("state", "BANNED");
        }

        user.setState(User.State.valueOf(updateUser.getNewState()));
        user.setRole(User.Role.valueOf(updateUser.getNewRole()));

        usersRepository.save(user);

        return from(user);
    }

    @Override
    public TasksDto getAllTasksOfUser(Long userId) {

        User user = getUserOrThrow(userId);

        return TasksDto.builder()
                .tasks(from(user.getTasks()))
                .count(user.getTasks().size())
                .build();
    }

    @Override
    public UserDto deleteUser(Long userId) {
        User user = getUserOrThrow(userId);

        usersRepository.delete(user);

        return from(user);
    }

    private User getUserOrThrow(Long userId) {
        return usersRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User", userId));
    }
}
