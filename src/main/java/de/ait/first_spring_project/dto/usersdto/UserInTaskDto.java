package de.ait.first_spring_project.dto.usersdto;

import de.ait.first_spring_project.models.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(description = "Информация о пользователе которому назначено задание")
public class UserInTaskDto {

    @Schema(description = "Идентификатор пользователя", example = "2")
    private Long id;

    @Schema(description = "Email пользователя", example = "example@mail.com")
    private String email;

    public static UserInTaskDto from(User user){
        return UserInTaskDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .build();
    }
}
