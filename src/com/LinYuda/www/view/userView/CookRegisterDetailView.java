package com.LinYuda.www.view.userView;

import com.LinYuda.www.service.UserMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 厨师注册界面的详细信息输入框
 * 传入所有参数后将在内部调用register方法
 */
public class CookRegisterDetailView extends JDialog {
    public CookRegisterDetailView(RegisterView registerView, String account, String password, int permissionLevel, String userName, String location, String contact, int superiorId, int windowNo) {
        super(registerView, "输入信息", true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //添加输入上司id的标签和输入框
        JLabel superiorIdLabel = new JLabel("请输入上司id");
        GridBagConstraints superiorIdLabelGridBagConstraints = new GridBagConstraints();
        superiorIdLabelGridBagConstraints.gridx = 0;
        superiorIdLabelGridBagConstraints.gridy = 0;
        superiorIdLabelGridBagConstraints.gridwidth = 2;
        superiorIdLabelGridBagConstraints.gridheight = 1;
        superiorIdLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        superiorIdLabelGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        container.add(superiorIdLabel, superiorIdLabelGridBagConstraints);

        JTextField superiorIdTextField = new JTextField(20);
        GridBagConstraints superiorIdTextFieldGridBagConstraints = new GridBagConstraints();
        superiorIdTextFieldGridBagConstraints.gridx = 0;
        superiorIdTextFieldGridBagConstraints.gridy = 1;
        superiorIdTextFieldGridBagConstraints.gridwidth = 2;
        superiorIdTextFieldGridBagConstraints.gridheight = 1;
        superiorIdTextFieldGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        container.add(superiorIdTextField, superiorIdTextFieldGridBagConstraints);


        //添加输入窗口编号的标签和输入框
        JLabel windowNoLabel = new JLabel("请输入负责的窗口编号");
        GridBagConstraints windowNoLabelGridBagConstraints = new GridBagConstraints();
        windowNoLabelGridBagConstraints.gridx = 0;
        windowNoLabelGridBagConstraints.gridy = 2;
        windowNoLabelGridBagConstraints.gridwidth = 2;
        windowNoLabelGridBagConstraints.gridheight = 1;
        windowNoLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        windowNoLabelGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        container.add(windowNoLabel, windowNoLabelGridBagConstraints);

        JTextField windowNoTextField = new JTextField(20);
        GridBagConstraints windowNoTextFieldGridBagConstraints = new GridBagConstraints();
        windowNoTextFieldGridBagConstraints.gridx = 0;
        windowNoTextFieldGridBagConstraints.gridy = 3;
        windowNoTextFieldGridBagConstraints.gridwidth = 2;
        windowNoTextFieldGridBagConstraints.gridheight = 1;
        windowNoTextFieldGridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        container.add(windowNoTextField, windowNoTextFieldGridBagConstraints);


        //创建确定按钮
        JButton checkButton = new JButton("确定");
        GridBagConstraints checkButtonGridBagConstraints = new GridBagConstraints();
        checkButtonGridBagConstraints.gridx = 0;
        checkButtonGridBagConstraints.gridy = 4;
        checkButtonGridBagConstraints.gridwidth = 1;
        checkButtonGridBagConstraints.gridheight = 1;
        checkButtonGridBagConstraints.anchor = GridBagConstraints.WEST;
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //检索数据是否为空
                String superiorIdString = superiorIdTextField.getText();
                String windowNoString = windowNoTextField.getText();
                if (superiorIdString.length() == 0) {
                    superiorIdLabel.setForeground(Color.RED);
                    superiorIdLabel.setText("上级id不能为空");
                }
                if (windowNoString.length() == 0) {
                    windowNoLabel.setForeground(Color.RED);
                    windowNoLabel.setText("窗口编号不能为空");
                }
                if (windowNoString.length() != 0 && superiorIdString.length() != 0) {
                    //调用注册方法
                    superiorIdLabel.setForeground(Color.BLACK);
                    windowNoLabel.setForeground(Color.BLACK);
                    long superiorId = Long.parseLong(superiorIdString);
                    int windowNo = Integer.parseInt(windowNoString);
                    UserMethod.register(account, password, permissionLevel, userName, location, contact, superiorId, windowNo);
                }

            }
        });
        container.add(checkButton, checkButtonGridBagConstraints);


        //创建取消按钮
        JButton cancelButton = new JButton("取消");
        GridBagConstraints cancelButtonGridBagConstraints = new GridBagConstraints();
        cancelButtonGridBagConstraints.gridx = 1;
        cancelButtonGridBagConstraints.gridy = 4;
        cancelButtonGridBagConstraints.gridwidth = 1;
        cancelButtonGridBagConstraints.gridheight = 1;
        cancelButtonGridBagConstraints.anchor = GridBagConstraints.EAST;
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(cancelButton, cancelButtonGridBagConstraints);


        setResizable(false);
        setVisible(true);
    }
}
