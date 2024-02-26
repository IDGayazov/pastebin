package service;

import dao.UserDao;
import dto.CreateUserDto;
import dto.UserDto;
import entity.User;
import exception.ValidationException;
import security.PasswordEncoder;
import security.Sha256PasswordEncoder;
import validation.Error;
import validation.CreateUserValidation;
import validation.Validation;
import validation.ValidationResult;
import util.*;


import java.sql.SQLException;
import java.util.Optional;


public class UserService {

    private static final UserService INSTANCE = new UserService();

    private static final UserDao userDao = UserDao.getInstance();
    
    private static final Validation<CreateUserDto> userValidator = CreateUserValidation.getInstance();
    
    private static final PasswordEncoder passwordEncoder = Sha256PasswordEncoder.getInstance();
    
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
    
    public int createUser(CreateUserDto createUserDto) throws SQLException {
    	
    	ValidationResult validationResult = new ValidationResult();
    	if(userDao.isExistsUser(createUserDto.getLogin())) {
    		validationResult.add(Error.of("invalid.login", "User with this login is already exists"));
    	}
    	validationResult.add(userValidator.isValid(createUserDto));
    	
    	if(validationResult.hasErrors()) {
    		throw new ValidationException(validationResult.getErrors());
    	}
    	
    	User user = userDao.save(User.builder()
    			.login(createUserDto.getLogin())
    			.email(createUserDto.getEmail())
    			.password(passwordEncoder.encode(createUserDto.getPassword()))
    			.build());
    	
    	return user.getId();
    }

    public Optional<User> getUserByLogin(String login) throws SQLException {
    	Optional<User> user = userDao.findByLogin(login);
    	return user;
    }
    
    private UserService(){

    }

    public static UserService getInstance(){
        return INSTANCE;
    }

}
