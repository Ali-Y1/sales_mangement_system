package Database;
import order.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLDatabaseConnection {
        // Connect to your database.
        // Replace server name, username, and password with your credentials
    private product p;
        public product fetchData(String str){
            p = new product();
            //String connectionUrl = "jdbc:sqlserver://DESKTOP-5PMRJ61:1433;databaseName=sales_mangment;user=sa@DESKTOP-5PMRJ61;password=aliali.aliali.313";
            ResultSet resultSet = null;
            String dbURL = "jdbc:sqlserver://DESKTOP-5PMRJ61;databaseName=sales_mangment";
            String user = "sa";
            String pass = "<your password>";
            try (Connection connection = DriverManager.getConnection(dbURL,user,pass);
                 Statement statement = connection.createStatement();) {

                // Create and execute a SELECT SQL statement.
                String selectSql = "SELECT * from products where name = '" + str + "'";
                resultSet = statement.executeQuery(selectSql);

                // Print results from select statement
                if (resultSet.next()) {
                    p.setName(resultSet.getString(2).trim().replaceAll(" +", " "));
                    p.setPrice(Integer.parseInt(resultSet.getString(3).trim().replaceAll(" +", " ")));
                }
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
            return p;
        }
    }



