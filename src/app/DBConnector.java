package app;

import java.sql.*;

/**
 * Created by root on 18.02.18.
 */
public class DBConnector {
    public static DBConnector shared = new DBConnector();

    private static final String CONN_URL = "jdbc:mysql://localhost/studio?"+"user=root&password=N@z@rtk@ch3";

    private Connection connection = null;


    private DBConnector() {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(CONN_URL);}

catch(ClassNotFoundException e){
            e.printStackTrace();


            }
            catch (SQLException e){
    e.printStackTrace();
        }
    }

    @Override
    public void finalize(){
        try {
            connection.close();

        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
    public Connection getConnect(){
        return connection;
    }





}
