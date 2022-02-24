package Database;
import order.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SQLDatabaseConnection {
        // Connect to your database.
        // Replace server name, username, and password with your credentials
        // class is based on singleton design pattern
    private static Connection connection;
        private Connection connect(){
            //ResultSet resultSet = null;
            String dbURL = "jdbc:sqlserver://DESKTOP-5PMRJ61;databaseName=sales_mangment";
            String user = "ali";
            String pass = "123456";

            try {
                connection = DriverManager.getConnection(dbURL,user,pass);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return connection;
        }
        public Connection getConnection(){
            if(connection != null)
                return connection;
            else
                return connect();
        }
}



