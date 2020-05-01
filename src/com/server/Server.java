package com.server;

public class Server {

    public static void start(int port){
        ServerConnector.connect(port);  //构造方法创建连接
        try {
            while (true) {
                try {

                    //accept
                    ServerConnector.export();//暴露服务打开网络端口接收外部请求，执行服务返回结果

                    //开启线程启动服务
                    new Thread(new ServerThread()).start();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            ServerConnector.closeServerSocket();//关闭服务
        }
    }
}
