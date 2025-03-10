package repository;

import model.User;
import java.util.List;

public class UserRepository extends BaseRepository<User> {
    private static UserRepository instance;
    public static int nextId = 1;

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
            List<User> users = instance.getAll();
            if (!users.isEmpty()) {
                nextId = users.stream()
                        .mapToInt(User::getId)
                        .max().orElse(0) + 1;
            }
        }
        return instance;
    }

    public UserRepository() {
        super("users_object.txt");
    }
}