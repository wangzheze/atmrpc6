package com.server;
//服务提供的功能
public interface CardService {
    String login(String user,String password);

    String inquiry(String user);

    String save(String user,double money);

    String withdraw(String user,double money);
}
