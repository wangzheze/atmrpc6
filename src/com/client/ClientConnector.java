package com.client;

import com.server.Server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
/*
封装网络操作
 */
public class ClientConnector {

    private static Socket socket;
    private static InputStream is;
    private static ObjectInputStream ois;
    private static OutputStream os;
    private static ObjectOutputStream oos;

    private static boolean connectSocket(Address address){  //打开一个网络Socket服务器端口
        try {
            System.out.println(address.getHost() + " " + address.getPort());
            socket = new Socket(address.getHost(), address.getPort());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }

        return !socket.isClosed() && socket.isConnected();
    }

    private static boolean connectServer(Address address){      //建立与远程服务器的连接
        if(connectSocket(address)){           //获取socket对象
            try {
                os = socket.getOutputStream();
                oos = new ObjectOutputStream(os);
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);
                return true;
            }catch (Exception e){
                System.out.println(e.getMessage());
                return false;
            }
        }
        return false;
    }

    public static boolean connect(){

        int length = LoadBalance.length();

        for(int i = 0;i < length;i++){
            Address address = LoadBalance.getAddress();

            if(connectServer(address)) {
                //LoadBalance.current = address;
                return true;
            }
           // else{
            //    LoadBalance.current = null;
            //}
        }
        return false;

    }
    //发送对象到服务器
    public static void send(Object obj) {
        try {

            oos.writeObject(obj);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
    //接收服务器返回的对象
    public static Object receive(){
        Object result = null;
        try{
            result = ois.readObject();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
       return result;
    }
    //关闭连接
    public static void close(){
        try {
            ois.close();
            oos.close();
            socket.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }




    public static void connectTest(String host,int port){

        try{
            socket = new Socket(host,port);

            OutputStream os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            InputStream is = socket.getInputStream();
            ois = new ObjectInputStream(is);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
