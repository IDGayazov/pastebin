package service;

import dto.ReadUserDto;
import entity.User;
import security.PasswordEncoder;
import security.Sha256PasswordEncoder;
import validation.Error;

import java.util.Optional;
import java.sql.SQLException;

public class AuthenticationService {
	
	private static final UserService userService = UserService.getInstance();
	
	private final static AuthenticationService INSTANCE = new AuthenticationService();
	
	private static final PasswordEncoder passwordEncoder = Sha256PasswordEncoder.getInstance();
	
	private AuthenticationService() {}
	
	public Optional<Error> login(ReadUserDto readUserDto) throws SQLException {
		
		Optional<User> user = userService.getUserByLogin(readUserDto.getLogin());
		
		readUserDto.setId(user.get().getId());
		
		Error error = null;
		if(!user.isPresent() || !user.get().getPassword().equals(passwordEncoder.encode(readUserDto.getPassword()))) {
			error = Error.of("invalid.login", "The username or password is incorrect");
		}
		return Optional.ofNullable(error);
	}
	
	public static AuthenticationService getInstance() {
		return INSTANCE;
	}
	
	
}
