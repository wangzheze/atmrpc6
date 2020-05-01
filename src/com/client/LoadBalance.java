package com.client;

import com.io.JSONReader;

import java.util.List;

public class LoadBalance {

    private static final String SERVER_PATH = "C:\\Users\\wangzhe\\Desktop\\网课\\分布式\\作业\\atmrpc6\\src\\com\\client\\\\server.json";//D:\\ideaws\\atmrpc2.0\\atmrpc6\\src\\com\\client\\server.json";

    private static final List<Address> servers = JSONReader.readServer(SERVER_PATH);

    private static int index = 0;
    public static Address current = null;

    public static Address getAddress(){
        if(index == servers.size()){
            index = 0;
        }
        if(current != null) return current;
        else {
            current = servers.get(index++);
            return current;
        }
    }

    public static int length(){
        return servers.size();
    }

    public static void main(String[] args) {
        for(int i = 0;i < servers.size();i++){
            Address address = servers.get(i);
            System.out.println(address.getHost() + " " + address.getPort());
        }
    }
}
