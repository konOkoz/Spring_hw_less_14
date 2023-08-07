package de.ait.first_spring_project.dto.usersdto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import de.ait.first_spring_project.models.User;

import java.util.List;
import java.util.stream.Collectors;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema (description = "Пользователь системы")
public class UserDto {

    @Schema (description = "Идентификатор пользователя", example = "1")
    private Long id;

    @Schema (description = "Email пользователя", example = "example@mail.com")
    private String email;

    @Schema (description = "Роль пользователя", example = "ADMIN")
    private String role;

    @Schema (description = "Статус пользователя", example = "CONFIRMED")
    private String state;

    public static UserDto from(User user) {
        return UserDto.builder()
                .id(user.getId())
                .email(user.getEmail())
                .state(user.getState().name())
                .role(user.getRole().name())
                .build();

    }

    public static List<UserDto> from(List<User> users){
        return users.stream()
                .map(UserDto::from)
                .collect(Collectors.toList());
    }
}
