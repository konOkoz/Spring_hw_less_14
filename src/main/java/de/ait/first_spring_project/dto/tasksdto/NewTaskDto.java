package de.ait.first_spring_project.dto.tasksdto;

import de.ait.first_spring_project.validation.constraints.CorrectDateValidator;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Данные для добавления задания")
@CorrectDateValidator
public class NewTaskDto {

    @NotNull
    @NotBlank
    @Schema(description = "Описание задания", example = "Текст с описанием")
    private String description;

    @NotNull
    @NotBlank
    @Schema(description = "Название задания", example = "Текст с названием задания")
    private String title;


    @NotNull
    @NotBlank
    @Schema(description = "Дата публикации в формате YYYY-MM-DD", example = "2023-10-11")
    private String startDate;

    @NotNull
    @NotBlank
    @Schema(description = "Дата публикации в формате YYYY-MM-DD", example = "2023-10-15")
    private String finishDate;

    @Schema(description = "Идентификатор пользователя", example = "1")
    private Long aboutUserId;

}
