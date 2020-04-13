package com.LinYuda.www.view.normalUserView;

import com.LinYuda.www.po.menu.ShoppingCar;
import com.LinYuda.www.po.user.StudentUser;
import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.view.errorView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 确定订单按钮
 * 包含一个确定下单按钮去调用下单方法
 */
public class CheckOrderView extends JFrame {
    public CheckOrderView(long id) {
        super("确定你的订单");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        //添加购物车内容显示框
        ProductMenu[] meals = ShoppingCar.getShoppingCarInstance().getShoppingCar();
        JList<ProductMenu> jList = new JList<>(meals);
        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane);


        //添加价格标签
        JLabel totalPrice = new JLabel("当前总共价格为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice() + "￥");
        container.add(totalPrice);


        //添加数量标签
        JLabel totalAmount = new JLabel("购物车总数为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice());
        container.add(totalAmount);


        //添加确定按钮
        JButton checkButton = new JButton("确定订单");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (meals.length != 0) {
                    StudentUser.takeOrder(id);
                    //下单后刷新界面数据
                    ProductMenu[] mealsTemp = ShoppingCar.getShoppingCarInstance().getShoppingCar();
                    jList.setListData(mealsTemp);
                    totalPrice.setText("当前总共价格为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice() + "￥");
                    totalAmount.setText("购物车总数为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice());
                } else {
                    new MessageView("下单错误", "未选种商品");
                }
            }
        });
        container.add(checkButton);


        setResizable(false);
        setVisible(true);
    }
}
