package com.LinYuda.www.view.cookView;

import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.user.CookUser;
import com.LinYuda.www.view.errorView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 单个商品修改属性的界面
 * 主要是通过传入的参数引导向不同的修改方法参数
 * 并解析出相应的值
 */
public class EditFieldView extends JFrame {
    public EditFieldView(String title, ProductMenu target, byte targetField) {
        super(title);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(500, 100);
        setLocationRelativeTo(null);
        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        //添加标签与输入框
        JLabel jLabel = new JLabel("请输入要修改的值");
        container.add(jLabel);

        JTextField jTextField = new JTextField(15);
        container.add(jTextField);


        //添加取消按钮
        JButton cancelButton = new JButton("取消");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(cancelButton);


        //添加确定按钮
        JButton checkButton = new JButton("确定");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String textValue = jTextField.getText();
                if (textValue.length() != 0) {
                    switch (targetField) {
                        case CookUser
                                .UPDATE_PRODUCT_AMOUNT: {

                            long value = Long.parseLong(textValue);
                            CookUser.updateProduct(target, CookUser.UPDATE_PRODUCT_AMOUNT, value);
                            dispose();
                            break;
                        }
                        case CookUser
                                .UPDATE_PRODUCT_COOK_NO: {

                            long value = Long.parseLong(textValue);
                            //验真厨师编号是否存在
                            if (CookUser.checkCookNo(value)) {
                                CookUser.updateProduct(target, CookUser.UPDATE_PRODUCT_COOK_NO, value);
                                dispose();
                            } else {
                                new MessageView("修改厨师编号", "厨师编号不存在");
                            }
                            break;
                        }
                        case CookUser
                                .UPDATE_PRODUCT_NAME: {

                            CookUser.updateProduct(target, CookUser.UPDATE_PRODUCT_NAME, textValue);
                            dispose();
                            break;
                        }
                        case CookUser
                                .UPDATE_PRODUCT_PRICE: {

                            double value = Double.parseDouble(textValue);
                            CookUser.updateProduct(target, CookUser.UPDATE_PRODUCT_PRICE, value);
                            dispose();
                            break;
                        }

                        case CookUser
                                .UPDATE_PRODUCT_WINDOW_NO: {

                            int value = Integer.parseInt(textValue);
                            CookUser.updateProduct(target, CookUser.UPDATE_PRODUCT_WINDOW_NO, value);
                            dispose();
                            break;
                        }
                    }

                } else {
                    new MessageView("修改商品", "修改失败，请选中商品");
                }
            }
        });
        container.add(checkButton);

        setResizable(false);
        setVisible(true);
    }
}
