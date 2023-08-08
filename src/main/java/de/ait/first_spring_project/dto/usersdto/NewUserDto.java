package de.ait.first_spring_project.dto.usersdto;

import de.ait.first_spring_project.validation.constraints.NotWeakPassword;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@Builder
@Schema(description = "Данные для добавления пользователя")
public class NewUserDto {

    @Schema(description = "Email пользователя", example = "example@mail.com" )
    @Email
    @NotNull
    @NotBlank
    private String email;

    @Schema(description = "Пароль пользователя", example = "Qwerty123!")
    @NotBlank
    @Size(min = 7, max = 25)
    @NotWeakPassword
    private String password;
}
