package com.server;

import java.io.FileInputStream;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DatabaseConnector {

    private static Connection conn;

    private static final String PATH = "C:\\Users\\wangzhe\\Desktop\\网课\\分布式\\作业\\atmrpc6\\src\\com\\server\\mysql.ini";//D:\\ideaws\\atmrpc2.0\\atmrpc6\\src\\com\\server\\mysql.ini";

    private static void connect(){

        //使用properties类加载属性文件（mysql.ini)

        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(PATH));
        }catch (Exception e){
            System.out.println("加载mysql.ini失败");
        }

        String driver = properties.getProperty("driver");
        String url = properties.getProperty("url");
        String user = properties.getProperty("user");
        String password = properties.getProperty("password");

        try{
            Class.forName(driver);                              //加载驱动
        }catch (ClassNotFoundException e){
            System.out.println("Class.forName()失败,driver有问题");
        }

        try{
            conn = DriverManager.getConnection(url,user,password);          //获取数据库连接
            conn.setAutoCommit(true);                           //开启自动提交，关闭事务
        }catch (Exception e){
            System.out.println("Connection对象获取失败");
        }
    }

    private static void invoke(Object object,String name,Object...param){   //参数为： 要调用的服务对象，方法名，方法所需参数
        Class clazz = object.getClass();
        Method[] methods = clazz.getDeclaredMethods();          //获取当前类的所以方法

        try{
            for(Method method : methods){
                if(method.getName().equalsIgnoreCase(name)){
                    method.invoke(object,param);                //通过反射执行服务，执行方法参数为（类对象，方法参数）
                    return;
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static <T> List<T> executeQuery(String sql,Class<T> clazz,Object...param){  //执行查询语句返回result保存在list中
        connect();

        List<T> list = new ArrayList<>();

        try(
                PreparedStatement pstmt = conn.prepareStatement(sql))       //创建一个预处理pstmt对象
        {
                int length = param.length;
                if(length > 0){
                    for(int i = 0;i < length;i ++){
                        pstmt.setObject(i + 1,param[i]);        //传入参数
                    }
                }

                try(
                        ResultSet rs = pstmt.executeQuery())                //获取结果集
                {
                        ResultSetMetaData rsmd = rs.getMetaData();          //创建ResultSetMetaData对象

                        int column = rsmd.getColumnCount();                 //使用rsmd获取Result的列数量
                        while (rs.next()){                                  //处理结果集
                            T t = clazz.getDeclaredConstructor().newInstance();

                            for(int i = 0;i < column;i++){
                                invoke(t,"set" + rsmd.getColumnName(i + 1),rs.getObject(i + 1));
                            }

                            list.add(t);
                        }

                        return list;
                }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static boolean executeStatement(String sql,Object...param){
        connect();

        try(
                PreparedStatement pstmt = conn.prepareStatement(sql))
        {
                for(int i = 0;i < param.length;i++){
                    pstmt.setObject(i + 1,param[i]);
                }

                return pstmt.executeUpdate() > 0;
        }catch (SQLException e){
                System.out.println(e.getMessage());
                return false;
        }

    }

}
