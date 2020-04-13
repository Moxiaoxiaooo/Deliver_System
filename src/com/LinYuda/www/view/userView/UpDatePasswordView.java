package com.LinYuda.www.view.userView;

import com.LinYuda.www.service.UserMethod;
import com.LinYuda.www.view.errorView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 更新密码用的视图
 */
public class UpDatePasswordView extends JFrame {


    public UpDatePasswordView(long id) {
        super("修改密码");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //设置修改密码的文本

        JLabel noticeText = new JLabel("输入新的密码");
        GridBagConstraints noticeTestGridBagConstraints = new GridBagConstraints();
        noticeTestGridBagConstraints.gridx = 0;
        noticeTestGridBagConstraints.gridy = 0;
        noticeTestGridBagConstraints.gridwidth = 2;
        noticeTestGridBagConstraints.gridheight = 1;
        container.add(noticeText, noticeTestGridBagConstraints);


        //创建密码输入框框

        JPasswordField passwordInputBox = new JPasswordField(20);
        GridBagConstraints passwordInputBoxGridBagConstraints = new GridBagConstraints();
        passwordInputBoxGridBagConstraints.gridx = 0;
        passwordInputBoxGridBagConstraints.gridy = 1;
        passwordInputBoxGridBagConstraints.gridwidth = 2;
        passwordInputBoxGridBagConstraints.gridheight = 1;
        container.add(passwordInputBox, passwordInputBoxGridBagConstraints);


        //创建取消按钮

        JButton cancelButton = new JButton("取消");
        GridBagConstraints cancelButtonGridBagConstraints = new GridBagConstraints();
        cancelButtonGridBagConstraints.gridx = 0;
        cancelButtonGridBagConstraints.gridy = 2;
        cancelButtonGridBagConstraints.gridwidth = 1;
        cancelButtonGridBagConstraints.gridheight = 1;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(cancelButton, cancelButtonGridBagConstraints);


        //创建确定按钮

        JButton checkButton = new JButton("确定");
        GridBagConstraints checkButtonGridBagConstraints = new GridBagConstraints();
        checkButtonGridBagConstraints.gridx = 1;
        checkButtonGridBagConstraints.gridy = 2;
        checkButtonGridBagConstraints.gridwidth = 1;
        checkButtonGridBagConstraints.gridheight = 1;
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String password = new String(passwordInputBox.getPassword());
                if ("".equals(password)) {
                    new MessageView("修改密码失败", "修改密码失败，请确定你输入了密码");
                } else {
                    UserMethod.updatePassword(id, password);
                    new MessageView("修改密码", "修改密码成功");
                }
            }
        });
        container.add(checkButton, checkButtonGridBagConstraints);

        setResizable(false);
        setVisible(true);
    }
}
