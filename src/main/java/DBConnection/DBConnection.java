package DBConnection;

import java.sql.*;

public class DBConnection {
    public static DBConnection instance;
    private Connection connection;
    private DBConnection(){
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/TODO_LIST","root","1234");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return connection;
    }

    public static DBConnection getInstance(){
        return instance==null?instance=new DBConnection():instance;
    }
}
