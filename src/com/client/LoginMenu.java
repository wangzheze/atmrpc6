package com.client;

//import com.io.RemoteCaller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static com.client.Client.invoke;

public class LoginMenu implements ActionListener {

    private static final String LOGIN = "login";
    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    private static final String classPath = CLASS_PREFIX + SERVICE;
    public static String currentUser;

    private JFrame frame;
    private JPanel p0, p1, p2, p3;//p4包括确认密码时的输入框；点击register按钮出现
    private JTextField userName;
    private JTextField passWord, passwordCheck;
    private JButton login;

    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        Class<?>[] paramTypes = {String.class,String.class};

        if(cmd.equals("login")){
            String user = userName.getText();
            String password = passWord.getText();
            Object[] param = {user,password};
            String result = Remote.invoke(classPath, LOGIN,paramTypes,param);
            System.out.println(result);
            switch (result) {
                case "登录成功":
                    JOptionPane.showMessageDialog(frame, "登陆成功");
                    currentUser = user;
                    new Menu();
                    frame.setVisible(false);
                    break;
                case "连接失败":
                    JOptionPane.showMessageDialog(frame, "连接失败");
                    break;
                case "登录失败":
                    JOptionPane.showMessageDialog(frame, "登陆失败！请确认用户名与密码");
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, "result为空");
            }
        }
    }

    public LoginMenu() {
        frame = new JFrame("ATM");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//设置窗口的点击右上角的x的处理方式，这里设置的是退出程序
        p0 = new JPanel();

        p0.add(new JLabel("银行ATM"));
        frame.add(p0);

        p1 = new JPanel();
        p1.add(new JLabel("user:"));
        userName = new JTextField(20);
        p1.add(userName);

        p2 = new JPanel();
        p2.add(new JLabel(" password:"));
        passWord = new JPasswordField(19);
        p2.add(passWord);

        p3 = new JPanel();
        login = new JButton("login");
        p3.add(login);

        frame.add(p1);
        frame.add(p2);

        frame.add(p3);

        frame.pack();
        frame.setVisible(true);
        show();
    }


    public void show() {

        login.addActionListener(this);//添加监视器
        frame.setBounds(500, 500, 350, 250);//设置大小
        frame.setLayout(new FlowLayout());//设置流式布局
    }

    public static void main(String[] args) {

        new LoginMenu();
    }
}

