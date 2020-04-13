package com.LinYuda.www.view.userView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.LinYuda.www.service.UserMethod;

/**
 * 账号注册界面
 * 学生老师用户不会开启详细信息注册界面
 * 厨师会开启
 * 其他的等级目前还无法使用
 */
public class RegisterView extends JDialog {

    public RegisterView(JFrame loginView) {

        super(loginView, "注册", true);
        Container container = getContentPane();
        container.setLayout(new GridBagLayout());
        setSize(400, 300);
        setLocationRelativeTo(null);

        //创建下拉选择框

        String[] identifies = {"学生", "老师", "厨师", "副经理", "总经理"};
        JComboBox identifyBox = new JComboBox(identifies);
        GridBagConstraints identifyBoxGridBagConstraints = new GridBagConstraints();
        identifyBoxGridBagConstraints.gridheight = 1;
        identifyBoxGridBagConstraints.gridwidth = 1;
        identifyBoxGridBagConstraints.gridx = 1;
        identifyBoxGridBagConstraints.gridy = 20;
        identifyBoxGridBagConstraints.anchor = GridBagConstraints.EAST;

        container.add(identifyBox, identifyBoxGridBagConstraints);


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

        container.add(passwordField, passwordGridBagConstraints);
        passwordGridBagConstraints.gridy = 4;
        passwordGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(passwordLabel, passwordGridBagConstraints);


        //创建用户名输入框

        JTextField userNameField = new JTextField(20);
        JLabel userNameLabel = new JLabel("请输入你的用户名");
        GridBagConstraints userNameGridBagConstraints = new GridBagConstraints();

        userNameGridBagConstraints.gridx = 0;
        userNameGridBagConstraints.gridy = 10;
        userNameGridBagConstraints.gridheight = 2;
        userNameGridBagConstraints.gridwidth = 2;
        container.add(userNameField, userNameGridBagConstraints);

        userNameGridBagConstraints.gridy = 8;
        userNameGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(userNameLabel, userNameGridBagConstraints);


        //创建住址输入框

        JTextField locationField = new JTextField(20);
        JLabel locationLabel = new JLabel("请输入你的住址");
        GridBagConstraints locationGridBagConstraints = new GridBagConstraints();

        locationGridBagConstraints.gridx = 0;
        locationGridBagConstraints.gridy = 14;
        locationGridBagConstraints.gridheight = 2;
        locationGridBagConstraints.gridwidth = 2;
        container.add(locationField, locationGridBagConstraints);

        locationGridBagConstraints.gridy = 12;
        locationGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(locationLabel, locationGridBagConstraints);

        //创建联系方式输入框

        JTextField contactField = new JTextField(20);
        JLabel contactLabel = new JLabel("请输入你的联系方式");
        GridBagConstraints contactGridBagConstraints = new GridBagConstraints();

        contactGridBagConstraints.gridx = 0;
        contactGridBagConstraints.gridy = 18;
        contactGridBagConstraints.gridheight = 2;
        contactGridBagConstraints.gridwidth = 2;
        container.add(contactField, contactGridBagConstraints);

        contactGridBagConstraints.gridy = 16;
        contactGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(contactLabel, contactGridBagConstraints);


        //创建注册按钮

        JButton registerButton = new JButton("注册");
        GridBagConstraints registerButtonGridBagConstraints = new GridBagConstraints();

        registerButtonGridBagConstraints.gridheight = 1;
        registerButtonGridBagConstraints.gridwidth = 1;
        registerButtonGridBagConstraints.gridx = 0;
        registerButtonGridBagConstraints.gridy = 20;
        registerButtonGridBagConstraints.anchor = GridBagConstraints.WEST;
        registerButton.addActionListener(new ActionListener() {
            /**
             * 注册按钮监听器，收集了注册界面所有信息后判断是否为空，不为空则调用注册方法
             * @param e 监听器事件
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = accountField.getText();
                String password = new String(passwordField.getPassword());
                String contact = contactField.getText();
                String userName = userNameField.getText();
                String location = locationField.getText();
                int permissionLevel = identifyBox.getSelectedIndex() + 1;
                if (password.length() == 0) {
                    passwordLabel.setForeground(Color.RED);
                    passwordLabel.setText("密码不能为空白");
                } else if (account.length() == 0) {
                    accountLabel.setForeground(Color.RED);
                    accountLabel.setText("帐号不能为空白");
                } else if (contact.length() == 0) {
                    contactLabel.setForeground(Color.RED);
                    contactLabel.setText("联系方式不能为空白");
                } else if (userName.length() == 0) {
                    userNameLabel.setForeground(Color.RED);
                    userNameLabel.setText("用户名字不能为空白");
                } else if (location.length() == 0) {
                    locationLabel.setForeground(Color.RED);
                    locationLabel.setText("住址不能为空白");
                } else {
                    passwordLabel.setForeground(Color.BLACK);
                    accountLabel.setForeground(Color.BLACK);
                    contactLabel.setForeground(Color.BLACK);
                    locationLabel.setForeground(Color.BLACK);
                    userNameLabel.setForeground(Color.BLACK);
                    if (permissionLevel == 1 || permissionLevel == 2) {
                        UserMethod.register(account, password, permissionLevel, userName, location, contact, 0, 0);
                    } else if (permissionLevel == 3) {
                        new CookRegisterDetailView(RegisterView.this, account, password, permissionLevel, userName, location, contact, 0, 0);
                    }
                }


            }
        });
        container.add(registerButton, registerButtonGridBagConstraints);

        setResizable(false);

        setVisible(true);
    }
}
