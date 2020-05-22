package com.test.demo.database;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;


public class kubeDatabase {


//    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://192.168.244.131:30006/test?setUnicode=true&characterEncoding=utf8&serverTimezone=UTC";
//    static final String USER = "root";
//    static final String PASS = "123456";

    static final String JDBC_DRIVER = "org.postgresql.Driver";
    static final String DB_URL = "jdbc:postgresql://192.168.244.130:5432/test_db";
    static final String USER = "postgres";
    static final String PASS = "postgres";



    public boolean queryLoginInfo(String username,String password)
    {
        Map<String,Object> map = new HashMap<>();
        map.put("q","w");

        Connection conn = null;
        PreparedStatement ppst = null;
        boolean result = false;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            StringBuffer sql= new StringBuffer();
            sql.append("SELECT password from userinfo where username = ?");
            ppst = conn.prepareStatement(sql.toString());
            ppst.setString(1,username);
            ResultSet rs = ppst.executeQuery();
            while(rs.next()){
                String pwd  = rs.getString("password");
                if(pwd.equals(password))
                {
                    System.out.println("账密验证通过");
                    result =  true;
                }
            }
            rs.close();
            ppst.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }


    public static void main(String []args)
    {
        kubeDatabase kd = new kubeDatabase();

        boolean result = kd.queryLoginInfo("xy","123");
        System.out.println(result);
    }
}
