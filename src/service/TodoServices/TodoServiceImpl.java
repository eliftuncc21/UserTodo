package service.TodoServices;

import constants.TodoConstants;
import constants.UserConstants;
import dto.Request.TodoRequestDto;
import dto.Response.TodoResponseDto;
import mapper.TodoMapper;
import model.Todo;
import repository.RepositoryFactory;
import repository.RepositoryType;
import repository.TodoRepository;
import repository.UserRepository;
import java.util.List;
import java.util.Scanner;

public class TodoServiceImpl implements TodoService{

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoServiceImpl() {
        this.userRepository = (UserRepository) RepositoryFactory.getRepository(RepositoryType.USER);
        this.todoRepository = (TodoRepository) RepositoryFactory.getRepository(RepositoryType.TODO);
    }

    @Override
    public void addTodo(TodoRequestDto todoRequestDto) {
        updateDescription(todoRequestDto);
        Todo newTodo = TodoMapper.mapToCreate(todoRequestDto);

        boolean userExists = userRepository.getAll().stream()
                .anyMatch(user -> user.getId() == newTodo.getUserId());

        if (!userExists) {
            System.out.println(UserConstants.USER_NOT_FOUND_EXCEPTION);
            return;
        }
        List<Todo> todoList = todoRepository.getAll();
        todoList.add(newTodo);
        todoRepository.saveAll(todoList);
        System.out.println(TodoConstants.TODO_ADDED_SUCCESSFULLY);
    }

    private void updateDescription(TodoRequestDto todoRequestDto) {
        while(todoRequestDto.getDescription().length() > 10 ){
            System.out.println(TodoConstants.DESCRIPTION_TOO_LONG);
            Scanner scanner = new Scanner(System.in);
            System.out.print("Description: ");
            String description = scanner.nextLine();
            todoRequestDto.setDescription(description);
        }
    }

    @Override
    public void updateTodo(Todo updateTodo, TodoRequestDto todoRequestDto) {
        updateDescription(todoRequestDto);
        TodoMapper.mapToUpdate(updateTodo,todoRequestDto);
        todoRepository.update(updateTodo);
        System.out.println(TodoConstants.TODO_UPDATED_SUCCESSFULLY);
    }

    @Override
    public void listTodo() {
        if(todoRepository.getAll().isEmpty()){
            System.out.println(todoRepository.getAll());
            System.out.println(TodoConstants.NO_TODO_ADDED);
        } else {
            System.out.println("Kayıtlı Todo:");
            for(Todo todo : todoRepository.getAll()){
                TodoResponseDto todoResponseDto = TodoMapper.map(todo);
                System.out.println(todoResponseDto);
            }
        }
    }

    @Override
    public void deleteTodo(int id) {
        todoRepository.delete(id);
    }

    @Override
    public void listTodoByUserId(TodoRequestDto todoRequestDto) {
        int userId = todoRequestDto.getUserId();

        boolean userExists = userRepository.getAll().stream()
                .anyMatch(user -> user.getId() == userId);

        if (!userExists) {
            System.out.println(UserConstants.USER_NOT_FOUND_EXCEPTION);
            return;
        }

        System.out.println("Kullanıcı ID: " + userId + " için Todo Listesi:");
        boolean foundTodos = false;

        for (Todo todo : todoRepository.getAll()) {
            if (todo.getUserId() == userId) {
                TodoResponseDto todoResponseDto = TodoMapper.map(todo);
                System.out.println(todoResponseDto);
                foundTodos = true;
            }
        }

        if (!foundTodos) {
            System.out.println(todoRepository.getAll());
            System.out.println(TodoConstants.TODO_NOT_FOUND_FOR_USER);
        }
    }
}