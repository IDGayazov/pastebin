package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String URL = PropertiesUtil.get("db.connection_url");
    private static final String USER = PropertiesUtil.get("db.user");
    private static final String PASSWORD = PropertiesUtil.get("db.password");

    static {
    	try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private DBManager(){

    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
