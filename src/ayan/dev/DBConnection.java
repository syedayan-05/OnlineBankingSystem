package ayan.dev;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
    public static Connection getConnection() {
        Connection connection = null;
        try {
//            String url ="jdbc:mysql://127.0.0.1:3306/banking";
            String url ="jdbc:mysql://localhost:3306/banking";
            String user = "root";
            String password = "Gv9@K$3rT!e#8zWqP^nL0xV&";

            connection= DriverManager.getConnection(url,user,password);
        } catch (Exception e) {
            System.out.println("Error  : " +e.getMessage());
        }
        return connection;

    }

}
