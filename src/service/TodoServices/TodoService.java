package service.TodoServices;

import dto.Request.TodoRequestDto;
import model.Todo;

public interface TodoService {
    void addTodo(TodoRequestDto todoRequestDto);
    void updateTodo(Todo updateTodo, TodoRequestDto todoRequestDto);
    void deleteTodo(int id);
    void listTodo();
    void listTodoByUserId(TodoRequestDto todoRequestDto);
}
