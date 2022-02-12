package Database;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import order.OrderInfo;
import order.Orders;
import order.customer;
import order.product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;

public class SQLQueries {
    SQLDatabaseConnection con = new SQLDatabaseConnection();
    Connection connection;

    // fetch product data from Database
    public product fetchProducts(String name){
        connection = con.getConnection();
        product p = new product();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from products where name = '" + name + "'";
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            if (resultSet.next()) {
                p.setName(resultSet.getString(2).trim().replaceAll(" +", " "));
                p.setPrice(Integer.parseInt(resultSet.getString(3).trim().replaceAll(" +", " ")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return p;
    }

    public void ADD_DB_Customer(customer c) {
        connection = con.getConnection();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            // Create and execute a SELECT SQL statement.
            String selectSql = "INSERT INTO customer" +
                    "  (c_fnname, c_phone, c_email,c_LnName,c_Address)\n" +
                    "VALUES" +
                    "  ('" +
                    c.getFnName() +
                    "','" +
                    c.getPhone() +
                    "','" +
                    c.getEmail() +
                    "','" +
                    c.getLnName() +
                    "','" +
                    c.getAddress() +
                    "');";
            statement.executeQuery(selectSql);
            // Print results from select statement
        } catch (SQLException e) {
            //System.out.println(e.getSQLState());
        }
    }

    public int fetchCustomer(int phone) {
        connection = con.getConnection();
        int id=-1;
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from customer where c_phone = " + phone ;
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            if (resultSet.next()) {
                 return Integer.parseInt(resultSet.getString(1).trim().replaceAll(" +", " "));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public customer fetchCustomerByID(int id) {
        connection = con.getConnection();
        customer c = new customer();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = null;

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from customer where id = " + id;
            resultSet = statement.executeQuery(selectSql);

            // Print results from select statement
            if (resultSet.next()) {
                c.setFnName(resultSet.getString(2).trim().replaceAll(" +", " "));
                c.setPhone(Integer.parseInt(resultSet.getString(3).trim().replaceAll(" +", " ")));
                c.setEmail(resultSet.getString(4).trim().replaceAll(" +", " "));
                c.setLnName(resultSet.getString(5).trim().replaceAll(" +", " "));
                c.setAddress(resultSet.getString(6).trim().replaceAll(" +", " "));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return c;
    }
    public ObservableList<OrderInfo> fetchOrders() {
        connection = con.getConnection();
        OrderInfo order;
        ObservableList<OrderInfo> orders = FXCollections.observableArrayList();
        ResultSet resultSet= null;;
        try {
            Statement statement = connection.createStatement();

            // Create and execute a SELECT SQL statement.
            String selectSql = "SELECT * from orders";
            resultSet = statement.executeQuery(selectSql);


            while(resultSet.next()){
                order = new OrderInfo();
                order.setId(Integer.parseInt(resultSet.getString(1).trim().replaceAll(" +", " ")));
                order.setPrice(Float.parseFloat(resultSet.getString(2).trim().replaceAll(" +", " ")));
                order.setDate(resultSet.getString(3));
                order.setStatus(resultSet.getString(4).trim().replaceAll(" +", " "));
                //order.setC((customer) resultSet.getObject(5));
                order.parseProduct(resultSet.getString(6));
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }

}
