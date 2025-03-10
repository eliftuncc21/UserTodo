package app;

import constants.GeneralConstant;
import constants.UserConstants;
import dto.Request.TodoRequestDto;
import dto.Request.UserRequestDto;
import mapper.UserMapper;
import model.User;
import repository.RepositoryFactory;
import repository.RepositoryType;
import repository.UserRepository;
import service.TodoServices.TodoServiceImpl;
import service.UserServices.UserServiceImpl;

import java.util.Scanner;

public class UserApp {

    private final Scanner scanner;
    private final UserServiceImpl userService;
    private final TodoServiceImpl todoService;


    public UserApp(UserServiceImpl userService, TodoServiceImpl todoService) {

        this.userService = userService;
        this.todoService = todoService;
        this.scanner = new Scanner(System.in);

    }

    public void startUserApp(){

        UserRequestDto userRequestDto = new UserRequestDto();

        while(true){

            System.out.println("\nMenü");
            System.out.println("1 - Kullanıcı Ekle");
            System.out.println("2 - Kullanıcıları Listele");
            System.out.println("3 - Kullanıcı Sil");
            System.out.println("4 - Kullanıcı Güncelle");
            System.out.println("5 - Kullanıcı Todo List");
            System.out.println("6 - Çıkış");
            System.out.print("Seçiminiz: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1: {
                        addUser(userRequestDto);
                        break;
                    }
                    case 2: {
                        userService.listUsers();
                        break;
                    }
                    case 3: {
                        deleteUSer();
                        break;
                    }
                    case 4: {
                        updateUser();
                        break;
                    }
                    case 5: {
                        todoList();
                        break;
                    }
                    case 6: {
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

    public void addUser(UserRequestDto userRequestDto) {

        System.out.print("Adınızı girin: ");
        String name = scanner.nextLine();

        System.out.print("Soyadınızı girin: ");
        String surname = scanner.nextLine();

        System.out.print("Yaşınızı girin: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        userRequestDto.setName(name);
        userRequestDto.setSurname(surname);
        userRequestDto.setAge(age);

        userService.addUser(userRequestDto);
    }

    public void deleteUSer(){

        System.out.print("Silmek istediğiniz ID: ");
        int deleteId = scanner.nextInt();
        scanner.nextLine();

        userService.deleteUser(deleteId);
    }

    public void updateUser() {

        System.out.print("Güncellemek istediğiniz ID: ");
        int updateId = scanner.nextInt();
        scanner.nextLine();

        UserRepository userRepository = (UserRepository) RepositoryFactory.getRepository(RepositoryType.USER);

        User updateUser = userRepository.getAll().stream()
                .filter(user -> user.getId() == updateId)
                .findFirst()
                .orElse(null);

        if (updateUser == null) {
            System.out.println(UserConstants.USER_NOT_FOUND_EXCEPTION);
            return;
        }

        System.out.print("Yeni ad: ");
        String newName = scanner.nextLine();

        System.out.print("Yeni soyad: ");
        String newSurname = scanner.nextLine();

        System.out.print("Yeni yaş: ");
        int newAge = scanner.nextInt();
        scanner.nextLine();

        UserRequestDto userRequestDto = new UserRequestDto();

        userRequestDto.setName(newName);
        userRequestDto.setSurname(newSurname);
        userRequestDto.setAge(newAge);

        UserMapper.mapUserUpdate(updateUser, userRequestDto);

        userService.updateUser(updateUser, userRequestDto);
    }


    public void todoList(){

        System.out.print("Kullanıcı ID'sini girin: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        TodoRequestDto todoRequestDto = new TodoRequestDto();
        todoRequestDto.setUserId(userId);

        todoService.listTodoByUserId(todoRequestDto);
    }
}