package de.ait.first_spring_project.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация об ошибке")
public class ErrorDto {

    @Schema(description = "Сообщение об ошибке", example = "Пользователь не найден")
    private String message;
}
