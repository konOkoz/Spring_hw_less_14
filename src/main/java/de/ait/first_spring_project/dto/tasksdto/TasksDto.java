package de.ait.first_spring_project.dto.tasksdto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Список задач пользователя")
public class TasksDto {

    @Schema(description = "Список всех задач пользователя")
    private List<TaskDto> tasks;

    @Schema(description = "Общее количество задач пользователя", example = "2")
    private Integer count;
}
