package de.ait.first_spring_project.dto.tasksdto;

import de.ait.first_spring_project.dto.usersdto.UserInTaskDto;
import de.ait.first_spring_project.models.Task;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "Задание для пользователя")
public class TaskDto {

    @Schema(description = "Индентификатор задания", example = "1")
    private Long id;

    @Schema(description = "Описание задания", example = "Текст с описанием задания")
    private String description;

    @Schema(description = "Название задания", example = "Текст с названием задания")
    private String title;

    @Schema(description = "Дата наздачения задания в формате YYYY-MM-DD", example = "2023-10-11")
    private String startDate;

    @Schema(description = "Дата до которой надо выполнить задание в формате YYYY-MM-DD", example = "2023-10-20")
    private String finishDate;

    @Schema(description = "Пользователь которому назначено задание")
    private UserInTaskDto executor;

    public static TaskDto from(Task task) {

        TaskDto result = TaskDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .title(task.getTitle())
                .startDate(task.getStartDate().toString())
                .finishDate(task.getFinishDate().toString())
                .build();

       if (task.getExecutor() != null) {
           result.setExecutor(UserInTaskDto.from(task.getExecutor()));
        }

        return result;
    }

    public static List<TaskDto> from(List<Task> tasks){
        return tasks.stream()
                .map(TaskDto::from)
                .collect(Collectors.toList());
    }

}
