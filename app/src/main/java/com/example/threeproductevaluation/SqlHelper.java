package com.example.threeproductevaluation;


import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;

//数据库管理器
public class SqlHelper {

    ResultSet rs;
    Statement stmt;
    Connection conn;
    PreparedStatement preparedStatement;//warning:我不知道我引对库没

    SqlHelper(){    //构造函数。用于初始化数据库
        try {
            String str_IP = "10.0.2.2"; //对真机：记得每次换网以后都要换IP地址
            Class.forName("com.mysql.jdbc.Driver");

            //虚拟机url: productevaluationdemo为数据库名
            String url="jdbc:mysql://" + str_IP + "/productevaluationdemo";
            //真机url
            //str_IP = ? ;
            //String url="jdbc:mysql://" + str_IP + "/productevaluationdemo";


            conn = DriverManager.getConnection(url, "root", "root");
            stmt = conn.createStatement();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i("Class SqlHelper", "SqlHelper()->ClassNotFoundException");
        } catch (SQLException e) {
            Log.i("Class SqlHelper", "SqlHelper()->SQLException");
        }
    }

    public void onQuery(String a_sql){    //用于查询数据
        try {
            rs = stmt.executeQuery(a_sql);
        } catch (SQLException e) {
            Log.i("Class SqlHelper", "onQuery()->SQLException");
        }
    }

    public void onUpdate(String a_sql){   //用于插入数据
        try {
            stmt.executeUpdate(a_sql);
        }
        catch (Exception e){
            Log.i("Class SqlHelper", "onUpdate()->Exception");
        }
    }

    public void onFinish(){    //用于结束数据库
        try{
            rs.close();
            stmt.close();
            conn.close();
        }
        catch (Exception e){
            Log.i("Class SqlHelper", "onFinish()->Exception");

        }
    }
}
