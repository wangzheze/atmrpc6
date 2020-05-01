package com.server;

import com.io.RemoteCaller;

import java.lang.reflect.Method;

public class ServerThread implements Runnable{

    private static final String CLASS_PREFIX = "com.server.";

    private static final String SERVICE = "CardService";
    private static final String classPath = CLASS_PREFIX + SERVICE;
    @Override
    public void run() {
        try {
            //获得客户端发来的远程请求对象
            RemoteCaller call = (RemoteCaller) ServerConnector.receive();
            String className = call.getClassName();
            Object[] params = call.getParams();
            String methodName = call.getMethodName();
            //处理
            Object result = null;
            switch (className){
                case classPath :
                    result = CardProxy.invoke(methodName,params);       //执行请求的服务
                    break;
            }
            call.setResult(result);         //将获取的结果Object注入call

            ServerConnector.send(call);         //向客户端发送包含了执行结果的call对象
            ServerConnector.closeSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
   /* @Override
    public void run() {
        try {
            //获得远程请求对象
            RemoteCaller call = (RemoteCaller) ServerConnector.receive();

            //处理
            Class<?> clazz = Class.forName(call.getClassName());

            Method m = clazz.getMethod(call.getMethodName(), call.getParamTypes());
            //一般接口而不是对象
            CardService o = (CardService) DynamicProxyFactory.getProxy(CardService.class,new CardServiceImp());

            Object[] params = call.getParams();

            Object result = null;

            switch (m.getName()) {

                case "login":
                    //登录
                    result = o.login((String) params[0], (String) params[1]);
                    break;
                case "save":
                //存钱
                    result = o.save((String) params[0],(double) params[1]);
                    break;
                case "inquiry":
                //查询余额
                    result = o.inquiry((String) params[0]);
                    break;
                case "withdraw":
                //取钱
                    result = o.withdraw((String) params[0],(double) params[1]);

            }
            //发送
            call.setResult(result);

            ServerConnector.send(call);

            ServerConnector.closeSocket();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
*/
}

            /* Class<?> clazz = Class.forName(call.getClassName());

            Method m = clazz.getMethod(call.getMethodName(), call.getParamTypes());
            Object o = DynamicProxyFactory.getProxy(clazz);

            CardService instance = (CardService) DynamicProxyFactory.getProxy(clazz);


            Object result = m.invoke(o, call.getParams());*/
            //---------------------
            /* String className = call.getClassName();
            String methodName = call.getMethodName();
            Object[] params = call.getParams();
            Class<?> classType = Class.forName(className);
            Class<?>[] paramTypes = call.getParamTypes();
            Method method = classType.getMethod(methodName, paramTypes);

            //运算
            Object remoteObject = Registry.getObject(className);

            Object result = null;

            if (remoteObject == null) {
                throw new Exception(className + " 的远程对象不存在");
            } else {
                result = method.invoke(remoteObject, params);
            }*/