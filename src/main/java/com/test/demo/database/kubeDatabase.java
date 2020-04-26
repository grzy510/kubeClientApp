package com.test.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class kubeDatabase {

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://192.168.244.131:30006/test?setUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "123456";

    public static void main(String []args)
    {
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);

            stmt = conn.createStatement();
            String sql="SELECT * from test";;
            ResultSet rs = stmt.executeQuery(sql);

            while(rs.next()){
                String test  = rs.getString("test");
                String qwer = rs.getString("qwer");

                System.out.print("test: " + test);
                System.out.print("qwer: " + qwer);
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
