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
    private final String TAG = "Class SqlHelper";

    //mysql8.0以上
    //static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String USER = "root";
    static final String PASSWORD = "root";

    private Connection connection = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;//warning:我不知道我引对库没
    public static ResultSet resultSet = null;

    SqlHelper(){    //构造函数。用于初始化数据库

        try {
            // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
            Class.forName(JDBC_DRIVER);

            // ## 连接URL
            //连接URL为jdbc:mysql//服务器地址/数据库名?useSSL=false&serverTimezone=UTC
            String str_IP = null;
            String connect_url = null;

            //虚拟机IP:
            //str_IP = "10.0.2.2:3306";

            //真机IP
            //str_IP = "10.32.219.193:3306";

            //寝室无线网IP
            //str_IP ="10.63.214.246:3306";

            //connect_url ="jdbc:mysql://" + str_IP + "/pvd";

            connect_url = "jdbc:mysql://10.0.2.2:3306/pvd";
            Log.i(TAG, "connect_url = " + connect_url);


            connection = DriverManager.getConnection(connect_url, USER, PASSWORD);
            if(connection == null){
                Log.i(TAG, "SqlHelper():connection == null");
            }
            statement = connection.createStatement();
            if(statement == null){
                Log.i(TAG, "SqlHelper():statement == null");
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            Log.i(TAG, "SqlHelper()->ClassNotFoundException");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i(TAG, "SqlHelper()->SQLException");
        }
    }

    public ResultSet onQuery(String sql){    //用于查询数据
        try {
            resultSet = statement.executeQuery(sql);
            if(resultSet == null)
                Log.i(TAG, "onQuery:resultSet == null");
        } catch (SQLException e) {
            e.printStackTrace();
            Log.i("Class SqlHelper", "onQuery()->SQLException");
        }
        return resultSet;
    }

    public void onUpdate(String sql){   //用于更新数据
        try {
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("Class SqlHelper", "onUpdate()->Exception");
           /* StackTraceElement[] traceElements = e.getStackTrace();
            StringBuilder sb = new StringBuilder();
            sb.append(e.getMessage() + "\n");//1异常信息
            for (StackTraceElement se : traceElements) {
                sb.append(se + "\n");//1异常信息-抛出方法栈
            }*/
        }
    }

    public void onDelete(String sql){   //用于删除数据
        try {
            statement.executeUpdate(sql);
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("Class SqlHelper", "onDelete()->Exception");
        }
    }

    public void onFinish(){    //用于结束数据库
        try{
            resultSet.close();
            statement.close();
            connection.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.i("Class SqlHelper", "onFinish()->Exception");
        }
    }
}
