package com.server;
/*
第一步，编写一个服务功能实现类，用接口
 */
public class CardServiceImp implements CardService{
    @Override
    public String login(String user, String password) {
        boolean result = CardDAO.login(user,password);
        String ans = "";

        if(result) ans = "登录成功";
        else ans = "登录失败";

        return ans;
    }

    @Override
    public String inquiry(String user) {
        String ans = "";

        Card card = CardDAO.inquiry(user);
        //ans = "用户:" + card.getUser() + " " + "余额:" + card.getMoney();
        if(card != null) {
            ans = "用户:" + card.getUser() + "      余额" + card.getMoney();
        }
        return ans;
    }

    @Override
    public String save(String user, double money) {
        String ans = "";

        boolean result = CardDAO.save(user,money);
        if(result){
            Card card = CardDAO.inquiry(user);
            ans = "存款成功！！！ " + card.getUser() + "余额:" + card.getMoney();
        }


        return ans;
    }

    @Override
    public String withdraw(String user, double money) {
        String ans = "";
        boolean result = CardDAO.withdraw(user, money);

        if(result){
            Card card = CardDAO.inquiry(user);
            ans = "取款成功!!! " + card.getUser() + "账户余额:" + card.getMoney();
        }else{
            ans = "取款失败";
        }


        return ans;
    }
}
