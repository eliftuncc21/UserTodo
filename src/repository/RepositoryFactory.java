package repository;

import constants.GeneralConstant;

import java.util.HashMap;
import java.util.Map;

public class RepositoryFactory {
    private static final Map<RepositoryType, BaseRepository<?>> repositories = new HashMap<>();

    public static UserRepository getUserRepository() {
        if (!repositories.containsKey(RepositoryType.USER)) {
            repositories.put(RepositoryType.USER, UserRepository.getInstance());
        }
        return (UserRepository) repositories.get(RepositoryType.USER);
    }

    public static TodoRepository getTodoRepository() {
        if (!repositories.containsKey(RepositoryType.TODO)) {
            repositories.put(RepositoryType.TODO, TodoRepository.getInstance());
        }
        return (TodoRepository) repositories.get(RepositoryType.TODO);
    }

    public static BaseRepository<?> getRepository(RepositoryType type) {
        if (!repositories.containsKey(type)) {
            switch (type) {
                case USER -> repositories.put(type, getUserRepository());
                case TODO -> repositories.put(type, getTodoRepository());
                default -> throw new IllegalArgumentException(GeneralConstant.UNSUPPORTED_REPOSITORY_TYPE);
            }
        }
        return repositories.get(type);
    }
}
