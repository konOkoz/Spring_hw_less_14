package de.ait.first_spring_project.repositories;

import de.ait.first_spring_project.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRepository extends JpaRepository <Task, Long> {
}
