import app.App;
import repository.TodoRepository;
import repository.UserRepository;
import service.TodoServices.TodoServiceImpl;
import service.UserServices.UserServiceImpl;

public class Main {
    public static void main(String[] args) {

        UserRepository userRepository = UserRepository.getInstance();
        UserServiceImpl userService = new UserServiceImpl();
        TodoRepository todoRepository = TodoRepository.getInstance();
        TodoServiceImpl todoService = new TodoServiceImpl();
        App app = new App(userRepository, userService, todoService, todoRepository);
        app.startApplication();
    }
}
