package com.LinYuda.www.view.errorView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 一个通用的信息窗口
 * 输入想要显示的窗口标题与内容即可
 */
public class MessageView extends JDialog {
    public MessageView(String title, String message) {
        setTitle(title);
        setModal(true);
        setSize(300, 150);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());

        //设置错误信息标签
        GridBagConstraints messageGridBagConstraints = new GridBagConstraints();
        messageGridBagConstraints.gridx = 0;
        messageGridBagConstraints.gridy = 0;
        container.add(new JLabel(message), messageGridBagConstraints);

        //创建确定按钮
        JButton checkButton = new JButton("确定");
        GridBagConstraints checkGridBagConstraints = new GridBagConstraints();
        checkGridBagConstraints.gridx = 0;
        checkGridBagConstraints.gridy = 1;
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(checkButton, checkGridBagConstraints);

        setResizable(false);
        setVisible(true);
    }
}
