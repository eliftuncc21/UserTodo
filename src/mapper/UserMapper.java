package mapper;

import dto.Request.UserRequestDto;
import dto.Response.UserResponseDto;
import model.User;
import repository.UserRepository;
import java.time.LocalDateTime;

public class UserMapper {

    public static UserResponseDto map(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setFullName(user.getName() + " " + user.getSurname());
        dto.setAge(user.getAge());
        dto.setRegistrationDate(user.getRegistrationDate());
        return dto;
    }

    public static User mapUserCreate(UserRequestDto dto) {
        LocalDateTime registrationDate = LocalDateTime.now();
        User newUser = new User(UserRepository.nextId++,
                dto.getName(),
                dto.getSurname(),
                dto.getAge(),
                registrationDate);
        return newUser;
    }

    public static User mapUserUpdate(User updateUser, UserRequestDto dto){
        updateUser.setName(dto.getName());
        updateUser.setSurname(dto.getSurname());
        updateUser.setAge(dto.getAge());
        updateUser.setRegistrationDate(LocalDateTime.now());
        return updateUser;
    }
}