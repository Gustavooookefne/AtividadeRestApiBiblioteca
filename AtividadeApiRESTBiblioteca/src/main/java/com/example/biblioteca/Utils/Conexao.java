package com.example.biblioteca.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private static final String URL = "";
    private static final String USER = "";
    private static final String PASSW = "";

    public static Connection connection()throws SQLException{
        return DriverManager.getConnection(URL,USER,PASSW);
    }
}
