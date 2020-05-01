package com.server;

import com.io.JSONReader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
//用于生成代理对象
public class DynamicProxyFactory {
    //private static Object instance;

    public static String beforeMethod = "";
    public static String afterMethod = "";

    private final static String AOP_PATH = "C:\\Users\\wangzhe\\Desktop\\网课\\分布式\\作业\\atmrpc6\\src\\com\\server\\aop.json";//D:\\ideaws\\atmrpc2.0\\atmrpc6\\src\\com\\server\\aop.json";

    public static Object getProxy(final Class<?> clazz,final Object instance){
        //目标服务对象类型
        InvocationHandler handler = (proxy, method, args) -> {
        //代理对象类
                String methodName = method.getName();

                //读取aop.json
                JSONReader.readAOP(AOP_PATH,methodName);

                //AOP编程
                Class<?> classType = MyAspect.class;

                //前
                runAOP(beforeMethod,classType,args);

                //中心方法
                Object result = method.invoke(instance,args);

                //后
                runAOP(afterMethod,classType,args);
                beforeMethod = "";
                afterMethod = "";
                return result;
        };

        System.out.println("DynamicProxy开始执行");


        return  Proxy.newProxyInstance(clazz.getClassLoader(),new Class<?>[] {clazz},handler);//生成动态代理对象
    }

    private static void runAOP(String aopMethod,final Class<?> classType,Object...args)throws Throwable{
        if(aopMethod != null && aopMethod.length() > 0){
            Object o = classType.getDeclaredConstructor().newInstance();
            Method[] methods = classType.getDeclaredMethods();
            for(Method m : methods){
                if(m.getName().equalsIgnoreCase(aopMethod)) m.invoke(o,args);
            }
        }
    }
}
