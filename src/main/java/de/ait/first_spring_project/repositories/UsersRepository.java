package de.ait.first_spring_project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import de.ait.first_spring_project.models.User;

public interface UsersRepository extends JpaRepository <User, Long>{
}
