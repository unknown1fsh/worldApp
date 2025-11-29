package com.example.world.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/world";
    private static final String USER = "root";
    private static final String PASSWORD = "12345";
    
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver bulunamadı", e);
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void testConnection() {
        try (Connection conn = getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("Veritabanı bağlantısı başarılı!");
            }
        } catch (SQLException e) {
            System.err.println("Veritabanı bağlantı hatası: " + e.getMessage());
            throw new RuntimeException("Veritabanına bağlanılamadı", e);
        }
    }
}

