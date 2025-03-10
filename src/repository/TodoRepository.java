package repository;

import model.Todo;
import java.util.List;

public class TodoRepository extends BaseRepository<Todo> {
    private static TodoRepository instance;
    public static int nextId = 1;

    public static TodoRepository getInstance() {
        if (instance == null) {
            instance = new TodoRepository();
            List<Todo> todos = instance.getAll();
            if (!todos.isEmpty()) {
                nextId = todos.stream()
                        .mapToInt(Todo::getId)
                        .max().orElse(0) + 1;
            }
        }
        return instance;
    }

    public TodoRepository() {
        super("todo.txt");
    }
}