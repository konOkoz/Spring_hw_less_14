package de.ait.first_spring_project.dto.tasksdto;

import de.ait.first_spring_project.validation.constraints.BeforeCurrentDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;


@Data
@Schema(description = "Данные для добавления задания")
public class NewTaskDto {

    @NotNull
    @NotBlank
    @Schema(description = "Описание задания", example = "Текст с описанием")
    private String description;

    @NotNull
    @NotBlank
    @Schema(description = "Название задания", example = "Текст с названием задания")
    private String title;


    @NotNull(message = "Дата начала не может быть пустой")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Плохой формат даты. Используйте формат YYYY-MM-DD")
    @BeforeCurrentDate
    @Schema(description = "Дата публикации в формате YYYY-MM-DD", example = "2023-10-11")
    private String startDate;

    @NotNull(message = "Дата окончания не может быть пустой")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "Плохой формат даты. Используйте формат YYYY-MM-DD")
    @BeforeCurrentDate
    @Schema(description = "Дата публикации в формате YYYY-MM-DD", example = "2023-10-15")
    private String finishDate;

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long aboutUserId;

}
