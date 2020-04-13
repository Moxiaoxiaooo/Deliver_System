package com.LinYuda.www.view.cookView;


import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.user.CookUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddProductView extends JFrame {
    public AddProductView() {
        super("添加商品");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new GridBagLayout());


        //厨师id输入框
        JLabel cookIdLabel = new JLabel("输入厨师id");
        GridBagConstraints cookIdLabelGridBagConstraints = new GridBagConstraints();
        cookIdLabelGridBagConstraints.gridx = 0;
        cookIdLabelGridBagConstraints.gridy = 0;
        cookIdLabelGridBagConstraints.gridwidth = 2;
        cookIdLabelGridBagConstraints.gridheight = 1;
        cookIdLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(cookIdLabel, cookIdLabelGridBagConstraints);

        JTextField cookIdTextField = new JTextField(15);
        GridBagConstraints cookIdTextFieldGridBagConstraints = new GridBagConstraints();
        cookIdTextFieldGridBagConstraints.gridx = 0;
        cookIdTextFieldGridBagConstraints.gridy = 1;
        cookIdTextFieldGridBagConstraints.gridwidth = 2;
        cookIdTextFieldGridBagConstraints.gridheight = 1;
        container.add(cookIdTextField, cookIdTextFieldGridBagConstraints);


        //商品名字输入框
        JLabel mealNameLabel = new JLabel("输入商品名字");
        GridBagConstraints mealNameLabelGridBagConstraints = new GridBagConstraints();
        mealNameLabelGridBagConstraints.gridx = 0;
        mealNameLabelGridBagConstraints.gridy = 2;
        mealNameLabelGridBagConstraints.gridwidth = 2;
        mealNameLabelGridBagConstraints.gridheight = 1;
        mealNameLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(mealNameLabel, mealNameLabelGridBagConstraints);

        JTextField mealNameTextField = new JTextField(15);
        GridBagConstraints mealNameTextFieldGridBagConstraints = new GridBagConstraints();
        mealNameTextFieldGridBagConstraints.gridx = 0;
        mealNameTextFieldGridBagConstraints.gridy = 3;
        mealNameTextFieldGridBagConstraints.gridwidth = 2;
        mealNameTextFieldGridBagConstraints.gridheight = 1;
        container.add(mealNameTextField, mealNameTextFieldGridBagConstraints);


        //价格输入框
        JLabel priceLabel = new JLabel("输入价格");
        GridBagConstraints priceLabelGridBagConstraints = new GridBagConstraints();
        priceLabelGridBagConstraints.gridx = 0;
        priceLabelGridBagConstraints.gridy = 4;
        priceLabelGridBagConstraints.gridwidth = 2;
        priceLabelGridBagConstraints.gridheight = 1;
        priceLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(priceLabel, priceLabelGridBagConstraints);

        JTextField priceTextField = new JTextField(15);
        GridBagConstraints priceTextFieldGridBagConstraints = new GridBagConstraints();
        priceTextFieldGridBagConstraints.gridx = 0;
        priceTextFieldGridBagConstraints.gridy = 5;
        priceTextFieldGridBagConstraints.gridwidth = 2;
        priceTextFieldGridBagConstraints.gridheight = 1;
        container.add(priceTextField, priceTextFieldGridBagConstraints);


        //数量输入框
        JLabel amountLabel = new JLabel("输入商品数量");
        GridBagConstraints amountLabelGridBagConstraints = new GridBagConstraints();
        amountLabelGridBagConstraints.gridx = 0;
        amountLabelGridBagConstraints.gridy = 6;
        amountLabelGridBagConstraints.gridwidth = 2;
        amountLabelGridBagConstraints.gridheight = 1;
        amountLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(amountLabel, amountLabelGridBagConstraints);

        JTextField amountTextField = new JTextField(15);
        GridBagConstraints amountTextFieldGridBagConstraints = new GridBagConstraints();
        amountTextFieldGridBagConstraints.gridx = 0;
        amountTextFieldGridBagConstraints.gridy = 7;
        amountTextFieldGridBagConstraints.gridwidth = 2;
        amountTextFieldGridBagConstraints.gridheight = 1;
        container.add(amountTextField, amountTextFieldGridBagConstraints);


        //窗口编号输入框
        JLabel windowNoLabel = new JLabel("输入商品窗口编号");
        GridBagConstraints windowNoLabelGridBagConstraints = new GridBagConstraints();
        windowNoLabelGridBagConstraints.gridx = 0;
        windowNoLabelGridBagConstraints.gridy = 8;
        windowNoLabelGridBagConstraints.gridwidth = 2;
        windowNoLabelGridBagConstraints.gridheight = 1;
        windowNoLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(windowNoLabel, windowNoLabelGridBagConstraints);

        JTextField windowNoTextField = new JTextField(15);
        GridBagConstraints windowNoTextFieldGridBagConstraints = new GridBagConstraints();
        windowNoTextFieldGridBagConstraints.gridx = 0;
        windowNoTextFieldGridBagConstraints.gridy = 9;
        windowNoTextFieldGridBagConstraints.gridwidth = 2;
        windowNoTextFieldGridBagConstraints.gridheight = 1;
        container.add(windowNoTextField, windowNoTextFieldGridBagConstraints);


        //创建类型下拉选择框
        JLabel typesLabel = new JLabel("选择类型");
        GridBagConstraints typesLabelGridBagConstraints = new GridBagConstraints();
        typesLabelGridBagConstraints.gridx = 0;
        typesLabelGridBagConstraints.gridy = 10;
        typesLabelGridBagConstraints.gridwidth = 2;
        typesLabelGridBagConstraints.gridheight = 1;
        typesLabelGridBagConstraints.anchor = GridBagConstraints.WEST;
        container.add(typesLabel, typesLabelGridBagConstraints);

        String[] types = {"", "粤菜", "川菜", "湘菜"};
        JComboBox typesComboBox = new JComboBox(types);
        GridBagConstraints typesComboBoxGridBagConstraints = new GridBagConstraints();
        typesComboBoxGridBagConstraints.gridx = 0;
        typesComboBoxGridBagConstraints.gridy = 11;
        typesComboBoxGridBagConstraints.gridwidth = 2;
        typesComboBoxGridBagConstraints.gridheight = 1;
        typesComboBoxGridBagConstraints.fill = GridBagConstraints.BOTH;
        container.add(typesComboBox, typesComboBoxGridBagConstraints);


        //创建确定按钮
        JButton checkButton = new JButton("确定");
        GridBagConstraints checkButtonGridBagConstraints = new GridBagConstraints();
        checkButtonGridBagConstraints.gridx = 1;
        checkButtonGridBagConstraints.gridy = 12;
        checkButtonGridBagConstraints.gridwidth = 1;
        checkButtonGridBagConstraints.gridheight = 1;
        checkButtonGridBagConstraints.anchor = GridBagConstraints.EAST;
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String amountString = amountTextField.getText();
                String mealName = mealNameTextField.getText();
                String priceString = priceTextField.getText();
                String cookIdString = cookIdTextField.getText();
                String windowNoString = windowNoTextField.getText();
                //检索填写的框是否为空
                if (amountString.length() == 0) {
                    amountLabel.setForeground(Color.RED);
                    amountLabel.setText("库存不能为空白");
                }
                if (mealName.length() == 0) {
                    mealNameLabel.setForeground(Color.RED);
                    mealNameLabel.setText("名字不能为空白");
                }
                if (priceString.length() == 0) {
                    priceLabel.setForeground(Color.RED);
                    priceLabel.setText("价格不能为空白");
                }
                if (cookIdString.length() == 0) {
                    cookIdLabel.setForeground(Color.RED);
                    cookIdLabel.setText("厨师id不能为空白");
                }
                if (windowNoString.length() == 0) {
                    windowNoLabel.setForeground(Color.RED);
                    windowNoLabel.setText("窗口编号不能为空白");
                }
                if (typesComboBox.getSelectedIndex() == 0) {
                    typesLabel.setForeground(Color.RED);
                    typesLabel.setText("类型不能为空");
                }
                if (amountString.length() != 0 && mealName.length() != 0
                        && priceString.length() != 0 && cookIdString.length() != 0
                        && windowNoString.length() != 0 && typesComboBox.getSelectedIndex() != 0) {

                    amountLabel.setForeground(Color.BLACK);
                    mealNameLabel.setForeground(Color.BLACK);
                    priceLabel.setForeground(Color.BLACK);
                    cookIdLabel.setForeground(Color.BLACK);
                    windowNoLabel.setForeground(Color.BLACK);

                    double price = Double.parseDouble(priceString);
                    int windowNo = Integer.parseInt(windowNoString);
                    long  cookNo = Long.parseLong(cookIdString);
                    long amount = Long.parseLong(amountString);
                    //String mealName;
                    String type = ProductMenu.getTypeName(typesComboBox.getSelectedIndex());
                    CookUser.insertProductMenu(mealName, price, cookNo, type, amount, windowNo);

                }
            }
        });
        container.add(checkButton, checkButtonGridBagConstraints);

        //创建取消按钮
        JButton cancelButton = new JButton("取消");
        GridBagConstraints cancelButtonGridBagConstraints = new GridBagConstraints();
        cancelButtonGridBagConstraints.gridx = 0;
        cancelButtonGridBagConstraints.gridy = 12;
        cancelButtonGridBagConstraints.gridwidth = 1;
        cancelButtonGridBagConstraints.gridheight = 1;
        cancelButtonGridBagConstraints.anchor = GridBagConstraints.WEST;
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
