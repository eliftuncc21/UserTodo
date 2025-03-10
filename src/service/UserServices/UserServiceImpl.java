package service.UserServices;

import constants.UserConstants;
import dto.Request.UserRequestDto;
import dto.Response.UserResponseDto;
import mapper.UserMapper;
import model.User;
import repository.RepositoryFactory;
import repository.RepositoryType;
import repository.UserRepository;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl() {
        this.userRepository = (UserRepository) RepositoryFactory.getRepository(RepositoryType.USER);
    }

    @Override
    public void addUser(UserRequestDto userRequestDto) {
        User newUser = UserMapper.mapUserCreate(userRequestDto);
        List<User> users = userRepository.getAll();
        users.add(newUser);
        userRepository.saveAll(users);
        System.out.println(UserConstants.USER_SAVED_SUCCESSFULLY);
    }

    @Override
    public void listUsers() {
        List<User> users = userRepository.getAll();
        if (users.isEmpty()) {
            System.out.println(UserConstants.USER_NOT_FOUND);
        } else {
            System.out.println("Kayıtlı kullanıcılar:");
            for (User user : users) {
                UserResponseDto userResponseDto = UserMapper.map(user);
                System.out.println(userResponseDto);
            }
        }
    }

    @Override
    public void deleteUser(int id) {
        userRepository.delete(id);
    }

    @Override
    public void updateUser(User updateUser, UserRequestDto userRequestDto) {
        updateUser = UserMapper.mapUserUpdate(updateUser, userRequestDto);
        userRepository.update(updateUser);
    }
}