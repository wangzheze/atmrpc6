package com.client;

import com.server.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static com.client.Client.invoke;

public class InquiryMenu implements ActionListener {


    private static final String INQUIRY = "inquiry";
    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    String classPath = CLASS_PREFIX + SERVICE;

    public JFrame iframe,frame;
    public JPanel ip0,ip1,ip2;
//  public JTextArea inquiry_result;
    public JButton confirm,cancel,back;
    public JLabel yue;

    public InquiryMenu(){
        iframe=new JFrame("查询");

        ip0=new JPanel();
        ip0.add(new JLabel("账户id:"+LoginMenu.currentUser));
        ip1=new JPanel();
        //yue=new JLabel("账户余额:"+Test.currentAccount.money);
        //ip1.add(yue);

        confirm=new JButton("查询");
        confirm.addActionListener(this);
        back = new JButton("返回");
        back.addActionListener(this);
        iframe.add(ip0);
        iframe.add(ip1);
        ip1.add(back);
        iframe.add(confirm);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);

        iframe.setBounds(500,500,350,300);

    }



    @Override
    public void actionPerformed(ActionEvent e) {

        String cmd = e.getActionCommand();
        if (cmd.equals("查询")) {
            Class<?>[] paramTypes = {String.class};
            Object[] param = {LoginMenu.currentUser};
            String result = Remote.invoke(classPath, INQUIRY, paramTypes, param);
            System.out.println(result);

            if (!result.equals("")) {

                JOptionPane.showMessageDialog(frame, result);

            } else {
                JOptionPane.showMessageDialog(frame, "查询失败!");
            }
        } else if (cmd.equals("返回")) {
            iframe.setVisible(false);
            new Menu();
        }
    }

    public static void main(String[] args) {
        InquiryMenu inquiryMenu = new InquiryMenu();
    }
}
