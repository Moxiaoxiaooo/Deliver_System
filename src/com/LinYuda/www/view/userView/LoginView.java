package com.LinYuda.www.view.userView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.LinYuda.www.service.UserMethod;

/**
 * 帐号登录界面
 */
public class LoginView extends JFrame {

    public LoginView() {
        super("登录外卖系统");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //创建帐号输入框

        JTextField accountField = new JTextField(20);
        JLabel accountLabel = new JLabel("请输入帐号");
        GridBagConstraints accountGridBagConstraints = new GridBagConstraints();

        accountGridBagConstraints.gridx = 0;
        accountGridBagConstraints.gridy = 2;
        accountGridBagConstraints.gridheight = 2;
        accountGridBagConstraints.gridwidth = 2;
        container.add(accountField, accountGridBagConstraints);

        accountGridBagConstraints.gridy = 0;
        accountGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(accountLabel, accountGridBagConstraints);


        //创建密码输入框

        JPasswordField passwordField = new JPasswordField(20);
        JLabel passwordLabel = new JLabel("请输入密码");
        GridBagConstraints passwordGridBagConstraints = new GridBagConstraints();

        passwordGridBagConstraints.gridx = 0;
        passwordGridBagConstraints.gridy = 6;
        passwordGridBagConstraints.gridwidth = 2;
        passwordGridBagConstraints.gridheight = 2;
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                if (password.length() == 0) {
                    passwordLabel.setForeground(Color.RED);
                    passwordLabel.setText("密码不能为空白");
                }
                if (account.length() == 0) {
                    accountLabel.setForeground(Color.RED);
                    accountLabel.setText("帐号不能为空白");
                }
                if (password.length() != 0 && account.length() != 0) {
                    passwordLabel.setForeground(Color.BLACK);
                    accountLabel.setForeground(Color.BLACK);
                    boolean check = UserMethod.login(account, password);
                    if (check) {
                        dispose();
                    }
                }
            }
        });
        container.add(passwordField, passwordGridBagConstraints);

        passwordGridBagConstraints.gridy = 4;
        passwordGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(passwordLabel, passwordGridBagConstraints);


        //创建登录按钮

        JButton loginButton = new JButton("登录");
        GridBagConstraints loginButtonGridBagConstraints = new GridBagConstraints();

        loginButtonGridBagConstraints.gridheight = 1;
        loginButtonGridBagConstraints.gridwidth = 1;
        loginButtonGridBagConstraints.gridx = 1;
        loginButtonGridBagConstraints.gridy = 9;
        loginButtonGridBagConstraints.anchor = GridBagConstraints.EAST;
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                if (password.length() == 0) {
                    passwordLabel.setForeground(Color.RED);
                    passwordLabel.setText("密码不能为空白");
                }
                if (account.length() == 0) {
                    accountLabel.setForeground(Color.RED);
                    accountLabel.setText("帐号不能为空白");
                }
                if (password.length() != 0 && account.length() != 0) {
                    passwordLabel.setForeground(Color.BLACK);
                    accountLabel.setForeground(Color.BLACK);
                    boolean check = UserMethod.login(account, password);
                    if (check) {
                        dispose();
                    }
                }
            }
        });
        container.add(loginButton, loginButtonGridBagConstraints);


        //创建注册按钮

        JButton registerButton = new JButton("注册");
        GridBagConstraints registerButtonGridBagConstraints = new GridBagConstraints();

        registerButtonGridBagConstraints.gridheight = 1;
        registerButtonGridBagConstraints.gridwidth = 1;
        registerButtonGridBagConstraints.gridx = 0;
        registerButtonGridBagConstraints.gridy = 9;
        registerButtonGridBagConstraints.anchor = GridBagConstraints.WEST;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new RegisterView(LoginView.this);
            }
        });
        container.add(registerButton, registerButtonGridBagConstraints);


        setResizable(false);
        setVisible(true);

    }


}
