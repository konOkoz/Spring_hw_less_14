package de.ait.first_spring_project.controllers.api;

import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.dto.tasksdto.TaskDto;
import de.ait.first_spring_project.dto.tasksdto.TasksDto;
import de.ait.first_spring_project.dto.usersdto.NewUserDto;
import de.ait.first_spring_project.dto.usersdto.UpdateUserDto;
import de.ait.first_spring_project.dto.usersdto.UserDto;
import de.ait.first_spring_project.dto.usersdto.UsersDto;
import de.ait.first_spring_project.validation.dto.ValidationErrorsDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Tags(value = {
        @Tag(name = "Users")
})

@RequestMapping("/api/users")
public interface UsersApi {

    @Operation(summary = "Создание пользователя", description = "Доступно только администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Пользователь создан",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    })
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<UserDto> addUser(@Parameter(required = true, description = "Пользователь") @RequestBody @Valid NewUserDto newUser);


    @Operation(summary = "Получение пользователя по айди", description = "Доступно всем")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
            content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "200", description = "Информация о пользователе",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @GetMapping("/{user-id}")
    ResponseEntity<UserDto> getUser(@Parameter(required = true, description = "Идентификатор пользователя", example = "1")
                    @PathVariable("user-id") Long userId);

    @Operation(summary = "Получения всех пользователей", description = "Доступно всем")
    @GetMapping
    ResponseEntity<UsersDto> getAllUsers();


    @Operation(summary = "Обновление пользователя", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
            content = {
                    @Content()
            }),
            @ApiResponse(responseCode = "200", description = "Обновленный пользователь",
            content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
    })
    @PutMapping("/{user-id}")
    ResponseEntity<UserDto> updateUser(@Parameter(required = true, description = "Идентификатор пользователя", example = "2")
                             @PathVariable("user-id") Long userId,
                             @RequestBody UpdateUserDto updateUser);

    @Operation(summary = "Назначение задания пользователю", description = "Доступно администратору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "422", description = "Пользователь с указанным ID не найден в системе",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "400", description = "Ошибка валидации",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorsDto.class))
                    }),
            @ApiResponse(responseCode = "201", description = "Назначенное задание",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDto.class))
                    })
    })
    @PostMapping("/{user-id}/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<TaskDto> addTask(@Parameter(required = true, description = "Идентификато пользователя", example = "2")
        @PathVariable("user-id") Long userId,
        @RequestBody @Valid NewTaskDto newTask);

    @Operation(summary = "Получение списка задач пользователя", description = "Доступно всем")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "404", description = "Пользователь не найден",
                    content = {
                            @Content()
                    }),
            @ApiResponse(responseCode = "200", description = "Список задач пользователя",
                    content = {
                            @Content(mediaType = "application/json", schema = @Schema(implementation = TasksDto.class))
                    })
    })
    @GetMapping("/{user-id}/tasks")
    ResponseEntity<TasksDto> getAllTasksOfUser(@Parameter(required = true, description = "Идентификатор пользователя", example = "2")
        @PathVariable("user-id") Long userId);


}


