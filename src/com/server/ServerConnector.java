package com.server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerConnector {

    private static ServerSocket serverSocket;
    private static Socket socket;
    private static InputStream is;
    private static ObjectInputStream ois;
    private static OutputStream os;
    private static ObjectOutputStream oos;

    public static void connect(int port){
        try {
            serverSocket = new ServerSocket(port);
            System.out.println(" 服务器启动......");
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    // 暴露服务，创建基于流的Socket
    public static void export(){
        try{
            socket = serverSocket.accept();//打开端口，准备接受外部连接

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);//对象输出
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);//对象读入
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    // 向客户发送包含了执行结果的对象
    public static void send(Object o){
        try {
            oos.writeObject(o);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //接收客户发送的对象
    public static Object receive(){
        Object result = null;
        try {
            result = ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
    //关闭socket
    public static void closeSocket(){
        try{
            ois.close();
            oos.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //关闭连接
    public static void closeServerSocket(){
        try {
            serverSocket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
