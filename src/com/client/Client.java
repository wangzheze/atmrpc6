package com.client;

import com.io.RemoteCaller;

public class Client {
   /* private static final String LOGIN = "login";
    private static final String INQUIRY = "inquiry";
    private static final String SAVE = "save";
    private static final String WITHDRAW = "withdraw";

    private static final String CLASS_PREFIX = "com.server.";

    private static final String SERVICE = "CardService";
    String classPath = CLASS_PREFIX + SERVICE;

    static String invoke(String classPath, String serviceName, Class<?>[] paramType, Object[] params){

        boolean conn = ClientConnector.connect();//建立连接对象

        String result = "";

        if(conn){

            RemoteCaller call = new RemoteCaller(classPath,serviceName,paramType,params);
            //new一个远程服务对象，其封装了调用相关的信息，参数为（服务名，方法名，参数类型，参数列表）

            //发送
            ClientConnector.send(call);//将远程服务对象发送给服务器

            //返回 问题?Server?
            call = (RemoteCaller) ClientConnector.receive();//接收服务器返回的对象

            //关闭资源
            ClientConnector.close();

            result = (String) call.getResult();//从对象中取出结果
        }

        return result;
    }
*/

    public static void main(String[] args) {

        new LoginMenu();

        /*String classPath = CLASS_PREFIX + SERVICE;

        Class<?>[] paramTypes = {String.class,String.class};

        Object[] param = {"123456",2500.0};

        String result = invoke(classPath, SAVE,paramTypes,param);

        System.out.println(result);*/

    }
}
