package service.UserServices;

import model.User;
import dto.Request.UserRequestDto;

public interface UserService {
    void addUser(UserRequestDto userRequestDto);
    void updateUser(User updateUser, UserRequestDto userRequestDto);
    void deleteUser(int id);
    void listUsers();
}
