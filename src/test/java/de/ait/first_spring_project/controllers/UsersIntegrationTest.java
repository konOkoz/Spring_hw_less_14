package de.ait.first_spring_project.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import de.ait.first_spring_project.dto.tasksdto.NewTaskDto;
import de.ait.first_spring_project.dto.usersdto.NewUserDto;
import de.ait.first_spring_project.dto.usersdto.UpdateUserDto;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("UsersController is works: ")
@DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
@ActiveProfiles("test")
public class UsersIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Nested
    @DisplayName("POST /api/users is works: ")
    class AddUserTests {
        @Test
        void add_user() throws Exception {
            String body = objectMapper.writeValueAsString(NewUserDto.builder()
                    .email("hard.test@mail.com")
                    .password("TestPassw0rd1!")
                    .build());

            mockMvc.perform(post("/api/users")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.email", is("hard.test@mail.com")))
                    .andExpect(jsonPath("$.role", is("USER")))
                    .andExpect(jsonPath("$.state", is("NOT_CONFIRMED")));
        }
    }

    @Nested
    @DisplayName("GET /api/users is works: ")
    class GetAllUsersTests {
        @Test
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void get_all_users() throws Exception {

            mockMvc.perform(get("api/users"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.count", is(2)));
        }
    }

    @Nested
    @DisplayName("DELETE /api/users/{userId} method is works: ")
    class DeleteUserTests {
        @Test
        @Sql(scripts = "/sql/data_for_users_sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void delete_exist_user() throws Exception {

            mockMvc.perform(delete("api/users/2"))
                    .andExpect(status().isOk());
        }

        @Test
        void delete_not_exist_user() throws Exception {
            mockMvc.perform(delete("/api/users/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("PUT /api/users/{userId} method is works: ")
    class UpdateUserTests {

        @Test
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void update_exist_user() throws Exception {
            String body = objectMapper.writeValueAsString(UpdateUserDto.builder()
                    .newRole("MANAGER")
                    .newState("CONFIRMED")
                    .build());

            mockMvc.perform(put("/api/users/1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("CONFIRMED")))
                    .andExpect(jsonPath("$.role", is("MANAGER")));
        }

        @Test
        void update_not_exist_user() throws Exception {

            String body = objectMapper.writeValueAsString(UpdateUserDto.builder()
                    .newRole("MANAGER")
                    .newState("CONFIRMED")
                    .build());

            mockMvc.perform(put("/api/users/1")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isNotFound());
        }

        @Test
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void update_users_as_admin() throws Exception {

            String body = objectMapper.writeValueAsString(UpdateUserDto.builder()
                    .newRole("ADMIN")
                    .newState("CONFIRMED")
                    .build());

            mockMvc.perform(put("/api/users/1")
                            .header("Content-Type", "application-json")
                            .content(body))
                    .andExpect(status().isForbidden());
        }
    }

    @Nested
    @DisplayName("GET /ap/users/{userId} method is works: ")
    class GetUserTests {

        @Test
        @Sql(scripts = "/sql/data_for_users.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        void get_exist_user() throws Exception {

            mockMvc.perform(get("/api/users/1"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.state", is("NOT_CONFIRMED")))
                    .andExpect(jsonPath("$.role", is("USER")));
        }

        @Test
        void get_not_exist_user() throws Exception {
            mockMvc.perform(get("/api/users/1"))
                    .andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /api/users/{userId}/tasks method is works;")
    class AddTasksToUserTests {

        @Test
        @Sql(scripts = "/sql/data_for_tasks.sql")
        @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
        public void add_task_for_exist_user() throws Exception {

            String body = objectMapper.writeValueAsString(NewTaskDto.builder()
                    .description("Test Task")
                    .title("Title of Test Task")
                    .startDate("2023-10-10")
                    .finishDate("2023-11-11")
                    .aboutUserId(1L)
                    .build());

            mockMvc.perform(post("/api/users/1/tasks")
                            .header("Content-Type", "application/json")
                            .content(body))
                    .andExpect(status().isCreated())
                    .andExpect(jsonPath("$.id", is(1)))
                    .andExpect(jsonPath("$.description", is("Test Task")))
                    .andExpect(jsonPath("$.title", is("Title of Test Task")))
                    .andExpect(jsonPath("$.startDate", is("2023-10-10")))
                    .andExpect(jsonPath("$.finishDate", is("2023-11-11")))
                    .andExpect(jsonPath("$.aboutUserId", is(1)))
                    .andExpect(jsonPath("$.about.email", is("test@mail.com")));
    }
    @Test
    public void add_task_for_not_exist_user() throws Exception {

            String body = objectMapper.writeValueAsString(NewTaskDto.builder()
                .description("Test Task")
                .title("Title of Test Task")
                .startDate("2023-10-10")
                .finishDate("2023-11-11")
                .aboutUserId(1L)
                .build());

            mockMvc.perform(post("/api/users/1/tasks")
                .header("Content-Type", "application/json")
                .content(body))
                .andExpect(status().isUnprocessableEntity());
    }
  }

  @Nested
  @DisplayName("GET /api/users/{usersId}/tasks")
  class GetTasksOfUserTest {
      @Test
      @Sql(scripts = "/sql/data_for_users.sql")
      @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
      void get_tasks_for_exist_user() throws Exception {

          mockMvc.perform(get("/api/users/1/tasks"))
                  .andExpect(status().isOk())
                  .andExpect(jsonPath("$.count", is(2)))
                  .andExpect(jsonPath("$.tasks[0].id", is(1)))
                  .andExpect(jsonPath("$.tasks[1].id", is (2)));
      }

      @Test
      void get_tasks_for_not_exist_user() throws Exception {

          mockMvc.perform(get("/api/users/1/tasks"))
                  .andExpect(status().isNotFound());
      }
  }
}
