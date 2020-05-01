package com.io;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.client.Address;
import com.server.DynamicProxyFactory;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONReader {

    private static JSONObject obj;

    private static void load(String path){
        try{
            File file = new File(path); //以当前路径来创建一个File对象

            String str = FileUtils.readFileToString(file);  //读取file返回字符串

            obj = JSON.parseObject(str);        //实例化obj
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public static void readAOP(String path,String methodName){      //读AOP
        load(path);

        JSONArray arr = obj.getJSONArray("aop");

        for(int i = 0;i < arr.size();i++){
            JSONObject temp = arr.getJSONObject(i);

            String targetMethod = temp.getString("targetMethod");

            if(targetMethod.equalsIgnoreCase(methodName)){
                String type = temp.getString("type");
                String adviceMethod = temp.getString("adviceMethod");

                if(type.equals("before")){
                    DynamicProxyFactory.beforeMethod = adviceMethod;
                }else {
                    DynamicProxyFactory.afterMethod = adviceMethod;
                }
            }

        }

    }

    public static List<Address> readServer(String path){        //读Server,返回Address的List
        load(path);

        List<Address> result = new ArrayList<>();

        JSONArray arr = obj.getJSONArray("server");

        for(int i = 0;i < arr.size();i++){
            Address address = new Address();
            address.setHost(arr.getJSONObject(i).getString("host"));
            address.setPort(arr.getJSONObject(i).getInteger("port"));
            result.add(address);
        }

        return result;
    }
}
