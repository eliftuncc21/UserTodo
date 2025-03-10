package app;

import constants.GeneralConstant;
import repository.RepositoryFactory;
import repository.RepositoryType;
import repository.TodoRepository;
import repository.UserRepository;
import service.TodoServices.TodoServiceImpl;
import service.UserServices.UserServiceImpl;

import java.util.Scanner;

public class App {

    private final Scanner scanner;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final TodoServiceImpl todoService;
    private final TodoRepository todoRepository;

    public App(UserRepository userRepository, UserServiceImpl userService,
               TodoServiceImpl todoService, TodoRepository todoRepository) {

        this.scanner = new Scanner(System.in);
        this.userRepository = (UserRepository) RepositoryFactory.getRepository(RepositoryType.USER);
        this.userService = userService;
        this.todoService = todoService;
        this.todoRepository = (TodoRepository) RepositoryFactory.getRepository(RepositoryType.TODO);

    }


    public void startApplication(){

        System.out.println("Hoşgeldiniz");

        UserApp userApp = new UserApp(userService, todoService);
        TodoApp todoApp = new TodoApp(todoService, todoRepository);

        while(true){

            System.out.println("işlem yapmak istediğiniz kategoriyi seçiniz: ");
            System.out.println("1 - User");
            System.out.println("2 - Todo");
            System.out.println("3 - Çıkış");
            System.out.print("Seçiminiz: ");

            try{
                int choice = scanner.nextInt();

                switch (choice) {
                    case 1: {
                        userApp.startUserApp();
                        break;
                    }
                    case 2: {
                        todoApp.startTodoApp();
                        break;
                    }
                    case 3: {
                        System.out.println("Çıkış yapılıyor...");
                        scanner.close();
                        return;
                    }
                    default:
                        System.out.println(GeneralConstant.INVALID_SELECTION);
                }
            } catch (Exception e) {
                scanner.nextLine();
                System.out.println(GeneralConstant.INVALID_NUMBER);
            }
        }
    }
}