package dao;

import entity.Paste;
import util.DBManager;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PasteDao implements Dao<Integer, Paste>{

    private static final PasteDao INSTANCE = new PasteDao();
    private static final String FIND_ALL = "SELECT * FROM paste";
    private static final String FIND_BY_ID = "SELECT * FROM paste " +
            "WHERE id = ?";

    private static final String SAVE = "INSERT INTO paste(paste_name, category, hash, create_date, expired_date, user_id, isPublic)" +
            "VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String FIND_BY_HASH = "SELECT * FROM paste WHERE hash = ?";

    private static final String FIND_HASH_BY_EXPIRED_DATE = "SELECT hash FROM paste WHERE expired_date < now()";
    private static final String DELETE_BY_HASH = "DELETE FROM paste WHERE hash = ?";
    private static final String FIND_BY_USER = "SELECT * FROM paste WHERE user_id = ?";

    private PasteDao(){

    }
    public static PasteDao getInstance(){
        return INSTANCE;
    }
    @Override
    public List<Paste> findAll() {
        return null;
    }
    public List<String> findAllHashByExpiredDate() throws SQLException {
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_HASH_BY_EXPIRED_DATE)){

            List<String> hashes = new ArrayList<>();

            var result = preparedStatement.executeQuery();
            while(result.next()){
                hashes.add(result.getString("hash"));
            }
            return hashes;
        }
    }
    @Override
    public Optional<Paste> findById(Integer id) throws SQLException {
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_BY_ID)) {

            preparedStatement.setObject(1, id);
            var result = preparedStatement.executeQuery();

            Paste paste = null;

            if (result.next()) {
                paste = buildPaste(result);
            }

            return Optional.ofNullable(paste);
        }
    }
    
    public List<Paste> findByUserId(int userId) throws SQLException{
    	
    	try(var connection = DBManager.getConnection();
    		var preparedStatement = connection.prepareStatement(FIND_BY_USER)){
    		
    		preparedStatement.setInt(1, userId);
    		ResultSet result = preparedStatement.executeQuery();
    		
    		List<Paste> pastes = new ArrayList<>();
    		while(result.next()) {
    			pastes.add(buildPaste(result));
    		}
    		return pastes;
    	}
    }

    public Optional<Paste> findByHash(String hash) throws SQLException{
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(FIND_BY_HASH)) {

            preparedStatement.setString(1, hash);
            var result = preparedStatement.executeQuery();

            Paste paste = null;

            if (result.next()) {
                paste = buildPaste(result);
            }

            return Optional.ofNullable(paste);
        }
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    public boolean deleteByHash(String hash) throws SQLException {
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(DELETE_BY_HASH)){

            preparedStatement.setObject(1, hash);
            int count = preparedStatement.executeUpdate();
            return count > 0;
        }
    }

    @Override
    public void update(Paste entity) {}

    @Override
    public Paste save(Paste entity) throws SQLException{
        try(var connection = DBManager.getConnection();
            var preparedStatement = connection.prepareStatement(SAVE, Statement.RETURN_GENERATED_KEYS);){

            preparedStatement.setObject(1, entity.getName());
            preparedStatement.setObject(2, entity.getCategory());
            preparedStatement.setObject(3, entity.getHash());
            preparedStatement.setObject(4, entity.getCreateDate());
            preparedStatement.setObject(5, entity.getExpiredDate());
            preparedStatement.setObject(6, entity.getUserId());
            preparedStatement.setObject(7, entity.isPublic());

            preparedStatement.executeUpdate();

            var generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            entity.setId(generatedKeys.getObject("id", Integer.class));
            return entity;
        }
    }

    private Paste buildPaste(ResultSet paste) throws SQLException {
        return Paste.builder()
                .id(paste.getObject("id", Integer.class))
                .name(paste.getObject("paste_name", String.class))
                .category(paste.getObject("category", String.class))
                .hash(paste.getObject("hash", String.class))
                .createDate(paste.getObject("create_date", Timestamp.class).toLocalDateTime())
                .expiredDate(paste.getObject("expired_date", Timestamp.class).toLocalDateTime())
                .userId(paste.getObject("user_id", Integer.class))
                .isPublic(paste.getObject("isPublic", Boolean.class))
                .build();
    }
}
