package de.ait.first_spring_project.models;

import lombok.*;


import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = "tasks")
@Entity
@Table(name = "users")
public class User {

    public enum Role {
        ADMIN,
        USER,
        MANAGER
    }

    public enum State {
        NOT_CONFIRMED,
        CONFIRMED,
        BANNED,
        DELETED
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String password;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @OneToMany(mappedBy = "executor")
    private List<Task> tasks;
}
