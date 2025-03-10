package app;

import constants.GeneralConstant;
import constants.TodoConstants;
import dto.Request.TodoRequestDto;
import model.Status;
import model.Todo;
import repository.RepositoryFactory;
import repository.RepositoryType;
import service.TodoServices.TodoServiceImpl;
import repository.TodoRepository;
import java.util.Scanner;

public class TodoApp {

    private final Scanner scanner;
    private final TodoServiceImpl todoService;
    private final TodoRepository todoRepository;

    public TodoApp(TodoServiceImpl todoService, TodoRepository todoRepository) {

        this.scanner = new Scanner(System.in);
        this.todoService = todoService;
        this.todoRepository = (TodoRepository) RepositoryFactory.getRepository(RepositoryType.TODO);

    }

    public void startTodoApp() {

        TodoRequestDto todoRequestDto = new TodoRequestDto();

        while (true) {

            System.out.println("\nMenü");
            System.out.println("1 - Todo Ekle");
            System.out.println("2 - Todo Listele");
            System.out.println("3 - Todo Sil");
            System.out.println("4 - Todo Güncelle");
            System.out.println("5 - Çıkış");
            System.out.print("Seçiminiz: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {
                    case 1: {
                        addTodo(todoRequestDto);
                        break;
                    }
                    case 2: {
                        todoService.listTodo();
                        break;
                    }
                    case 3: {
                        deleteTodo();
                        break;
                    }
                    case 4: {
                        updateTodo();
                        break;
                    }
                    case 5: {
                        System.out.println("Ana menüye dönülüyor...");
                        return;
                    }
                    default:
                        System.out.println(GeneralConstant.INVALID_SELECTION);
                }
            } catch (Exception e) {
                System.out.println(GeneralConstant.INVALID_LOGIN);
                scanner.nextLine();
            }
        }
    }

    public void addTodo(TodoRequestDto todoRequestDto) {

        System.out.print("Birden fazla todo eklemek ister misiniz? (Evet: 1, Hayır: 0): ");

        int multipleChoice = -1;

        while (multipleChoice != 1 && multipleChoice != 0) {
            try {
                multipleChoice = scanner.nextInt();
                scanner.nextLine();
                if (multipleChoice != 1 && multipleChoice != 0) {
                    System.out.println(GeneralConstant.INVALID_INPUT_SELECTION);
                }
            } catch (Exception e) {
                System.out.println(GeneralConstant.INVALID_INPUT_SELECTION);
                scanner.nextLine();
            }
        }

        if (multipleChoice == 1) {

            System.out.print("Kaç adet todo eklemek istiyorsunuz? ");
            int todoCount = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < todoCount; i++) {
                System.out.println("Todo " + (i + 1) + ":");
                setTodoDetails(todoRequestDto);
                todoService.addTodo(todoRequestDto);
            }
        } else {
            setTodoDetails(todoRequestDto);
            todoService.addTodo(todoRequestDto);
        }
    }

    public void setTodoDetails(TodoRequestDto todoRequestDto) {

        System.out.print("Title: ");
        String title = scanner.nextLine();

        System.out.print("Description: ");
        String description = scanner.nextLine();

        System.out.print("User ID: ");
        int userId = scanner.nextInt();

        scanner.nextLine();

        int statusChoice = getStatusChoice();
        Status status = Status.values()[statusChoice - 1];

        todoRequestDto.setTitle(title);
        todoRequestDto.setDescription(description);
        todoRequestDto.setUserId(userId);
        todoRequestDto.setStatus(status);
    }

    private int getStatusChoice() {

        int statusChoice = 0;

        while (statusChoice < 1 || statusChoice > 3) {

            System.out.println("Durum Seçin: ");
            System.out.println("1 - ACTIVE");
            System.out.println("2 - DONE");
            System.out.println("3 - CANCELLED");
            System.out.print("Seçiminiz: ");

            try {
                statusChoice = scanner.nextInt();
                scanner.nextLine();
                if (statusChoice < 1 || statusChoice > 3) {
                    System.out.println(TodoConstants.INVALID_SELECTION_ERROR);
                }
            } catch (Exception e) {
                System.out.println(TodoConstants.INVALID_SELECTION_ERROR);
                scanner.nextLine();
            }
        }
        return statusChoice;
    }

    public void deleteTodo() {

        System.out.print("Silmek istediğiniz Todo ID: ");
        int deleteId = scanner.nextInt();

        scanner.nextLine();

        todoService.deleteTodo(deleteId);
    }

    public void updateTodo() {

        TodoRequestDto todoRequestDto = new TodoRequestDto();

        System.out.print("Güncellemek istediğiniz Todo ID: ");
        int updateId = scanner.nextInt();

        scanner.nextLine();

        Todo updateTodo = todoRepository.getAll().stream()
                .filter(todo -> todo.getId() == updateId)
                .findFirst()
                .orElse(null);

        if (updateTodo == null) {
            System.out.println(TodoConstants.TODO_NOT_FOUND);
            return;
        }

        System.out.print("Yeni başlık: ");
        String newTitle = scanner.nextLine();

        System.out.print("Yeni açıklama: ");
        String newDescription = scanner.nextLine();

        int newStatusChoice = getStatusChoice();

        Status status = Status.values()[newStatusChoice - 1];

        todoRequestDto.setTitle(newTitle);
        todoRequestDto.setDescription(newDescription);
        todoRequestDto.setStatus(status);

        todoService.updateTodo(updateTodo, todoRequestDto);
    }
}