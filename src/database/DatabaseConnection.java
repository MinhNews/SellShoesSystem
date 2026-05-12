package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // NHỚ: Sửa lại Password cho đúng với máy của ông nhé
    private static final String URL = "jdbc:mysql://localhost:3306/QuanLyCuaHangGiay?useUnicode=true&characterEncoding=utf-8";
    private static final String USER = "root"; 
    private static final String PASSWORD = "020306"; 

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Lỗi kết nối CSDL!");
            return null;
        }
    }
}