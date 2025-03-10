package mapper;

import constants.UserConstants;
import dto.Request.TodoRequestDto;
import dto.Response.TodoResponseDto;
import model.Todo;
import model.User;
import repository.RepositoryFactory;
import repository.TodoRepository;
import repository.UserRepository;

import java.time.LocalDateTime;

public class TodoMapper {
    public static TodoResponseDto map(Todo todo){
        TodoResponseDto todoResponseDto = new TodoResponseDto();

        UserRepository userRepository = RepositoryFactory.getUserRepository();

        User user = userRepository.getAll().stream()
                .filter(u -> u.getId() == todo.getUserId())
                .findFirst()
                .orElse(null);
        String fullname = (user != null && user.getName() != null && user.getSurname() != null) ? user.getName() + " " + user.getSurname() : UserConstants.NO_SUCH_USER;

        todoResponseDto.setFullName(fullname);
        todoResponseDto.setTitle(todo.getTitle());
        todoResponseDto.setDescription(todo.getDescription());
        todoResponseDto.setUserId(todo.getUserId());
        todoResponseDto.setStatus(todo.getStatus());
        todoResponseDto.setRegistrCreate(todo.getRegistrCreate());
        return todoResponseDto;
    }

    public static Todo mapToCreate(TodoRequestDto dto){
        LocalDateTime registrCreate  = LocalDateTime.now();
        Todo newTodo = new Todo(TodoRepository.nextId++,
                dto.getTitle(),
                dto.getDescription(),
                dto.getUserId(),
                dto.getStatus(),
                registrCreate );
        return newTodo;
    }

    public static Todo mapToUpdate(Todo updateTodo, TodoRequestDto dto) {
        updateTodo.setTitle(dto.getTitle());
        updateTodo.setDescription(dto.getDescription());
        updateTodo.setUserId(dto.getUserId());
        updateTodo.setStatus(dto.getStatus());
        updateTodo.setRegistrCreate(LocalDateTime.now());

        return updateTodo;
    }
}