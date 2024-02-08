package service;

import dao.UserDao;
import dto.UserDto;
import entity.User;

import java.sql.SQLException;
import java.util.Optional;

public class UserService {

    private static final UserService INSTANCE = new UserService();

    private static final UserDao userDao = UserDao.getInstance();

    public UserDto getUserById(int id) throws SQLException {

        Optional<User> oUser = userDao.findById(id);
        if(!oUser.isPresent()){
            throw new RuntimeException("user not found");
        }

        User user = oUser.get();

        return UserDto.builder()
                .login(user.getLogin())
                .email(user.getEmail())
                .build();
    }

    private UserService(){

    }

    public static UserService getInstance(){
        return INSTANCE;
    }

}
