package com.server;

import java.util.HashMap;
import java.util.Map;
/*
服务器端
第三步，注册中心，用一个MAP集合保存所有服务的接口
 */
public class Registry {

    private static Map<String,Object> remoteObjects = new HashMap<>();//key:要调用的服务名称 value：服务对象CardServiceImp

    private static final String CLASS_PREFIX = "com.server.";

    static {

        remoteObjects.put(CLASS_PREFIX + "CardService",new CardServiceImp());

    }

   /* public static void registry(String className,Object object){

        remoteObjects.put(className,object);

    }*/

    public static Object getObject(String className){
        return remoteObjects.get(className);
    }



}
