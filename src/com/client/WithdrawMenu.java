package com.client;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//import static com.client.Client.invoke;

public class WithdrawMenu implements ActionListener {
    private static final String WITHDRAW = "withdraw";
    private static final String CLASS_PREFIX = "com.server.";
    private static final String SERVICE = "CardService";
    String classPath = CLASS_PREFIX + SERVICE;

    private JFrame iframe,wframe;
    private JTextField money;
    private JPanel ip0,ip1;
    private JButton confirm,cancel;

    public WithdrawMenu(){
        iframe = new JFrame("Withdraw");
        iframe.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        ip0 = new JPanel();
        ip0.add(new JLabel("取款："));
        money = new JTextField(20);
        ip0.add(money);

        ip1 = new JPanel();
        confirm = new JButton("确定");
        confirm.addActionListener(this);
        ip1.add(confirm);
        cancel = new JButton("返回");
        cancel.addActionListener(this);
        ip1.add(cancel);

        iframe.add(ip0);
        iframe.add(ip1);
        iframe.setLayout(new FlowLayout());
        iframe.setVisible(true);
        iframe.setBounds(500,500,350,300);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(cmd.equals("确定")){
            double textMoney = Double.parseDouble(money.getText());

            if(textMoney < 0){
                JOptionPane.showMessageDialog(iframe,"输入金额非法");
            }else if(textMoney > 20000){
                JOptionPane.showMessageDialog(wframe,"输入金额超过20000");
            }else {
                Class<?>[] paramTypes = {String.class, double.class};
                Object[] param = {LoginMenu.currentUser, textMoney};
                String result = Remote.invoke(classPath, WITHDRAW, paramTypes, param);
                if (!result.equals("")) {

                    JOptionPane.showMessageDialog(wframe, result);

                } else JOptionPane.showMessageDialog(wframe, "取款失败！");


            }

        }else if(cmd.equals("返回")){
            iframe.setVisible(false);
            new Menu();
        }
    }

    public static void main(String[] args) {
         new WithdrawMenu();
    }
}
