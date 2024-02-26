package dao;

import entity.User;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao implements Dao<Integer, User>{

    private static final UserDao INSTANCE = new UserDao();
    private static final String FIND_ALL = "SELECT * FROM users";
    private static final String FIND_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SAVE = "INSERT INTO users(login, email, password) VALUES(?, ?, ?)";
    private static final String IS_EXISTS_USER = "SELECT 1 FROM users WHERE login = ?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ?";

    private UserDao(){

    }

    public static UserDao getInstance(){
        return INSTANCE;
    }

    @Override
    public List<User> findAll() {
        try(var connection = DBManager.getConnection();
            var findAllStatement = connection.prepareStatement(FIND_ALL)){

            ResultSet usersResultSet = findAllStatement.executeQuery();

            List<User> usersList = new ArrayList<>();
            while(usersResultSet.next()){
                usersList.add(buildUser(usersResultSet));
            }
            return usersList;

        }catch(SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) throws SQLException {
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID)){

            preparedStatement.setInt(1, id);
            var result = preparedStatement.executeQuery();
            User user = null;
            if(result.next()){
                user = buildUser(result);
            }
            return Optional.ofNullable(user);
        }
    }
    
    public Optional<User> findByLogin(String login) throws SQLException{
    	
    	try(
    			var connection = DBManager.getConnection();
    			var preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
    	){
    		preparedStatement.setObject(1, login);
    		var result = preparedStatement.executeQuery();
    		
    		User user = null;
    		if(result.next()) {
    			user = buildUser(result);
    		}
    		
    		return Optional.ofNullable(user);	
    	}
    }
    
    public boolean isExistsUser(String login) throws SQLException{
    	try(
    			var connection = DBManager.getConnection();
    			var preparedStatement = connection.prepareStatement(IS_EXISTS_USER);){
    		
    		preparedStatement.setString(1, login);
    		var result = preparedStatement.executeQuery();
    		
    		if(result.next()) {
    			return true;
    		}
    		return false;
    	}
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public void update(User entity) {

    }

    @Override
    public User save(User entity) throws SQLException{
    	try(var connection = DBManager.getConnection();
    			var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS)){
    		
    		preparedStatement.setObject(1, entity.getLogin());
    		preparedStatement.setObject(2, entity.getEmail());
    		preparedStatement.setObject(3, entity.getPassword());
    		
    		preparedStatement.executeUpdate();
    		
    		var keys = preparedStatement.getGeneratedKeys();
    		keys.next();
    		entity.setId(keys.getObject("id", Integer.class));
    		return entity;	
    	}
    }
    
    private User buildUser(ResultSet usersResultSet) throws SQLException {
        return User.builder()
                .id(usersResultSet.getObject("id", Integer.class))
                .email(usersResultSet.getObject("email", String.class))
                .login(usersResultSet.getObject("login", String.class))
                .password(usersResultSet.getObject("password", String.class))
                .build();
    }
}
