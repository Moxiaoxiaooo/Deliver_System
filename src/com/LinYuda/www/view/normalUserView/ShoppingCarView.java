package com.LinYuda.www.view.normalUserView;

import com.LinYuda.www.po.menu.ProductMenu;
import com.LinYuda.www.po.menu.ShoppingCar;
import com.LinYuda.www.view.errorView.MessageView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 用户购物车界面
 * 因为JScrollPane未知bug采用流布局
 */
public class ShoppingCarView extends JFrame {

    public ShoppingCarView() {
        super("我的购物车");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);


        Container container = getContentPane();
        container.setLayout(new FlowLayout());


        //添加购物车内容显示框
        ProductMenu[] meals = ShoppingCar.getShoppingCarInstance().getShoppingCar();
        JList<ProductMenu> jList = new JList<>(meals);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane);


        //添加价格标签
        JLabel totalPrice = new JLabel("当前总共价格为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice() + "￥");
        container.add(totalPrice);


        //添加数量标签
        JLabel totalAmount = new JLabel("购物车总数为：" + ShoppingCar.getShoppingCarInstance().getAmount());
        container.add(totalAmount);


        //添加确定按钮
        JButton checkButton = new JButton("确定");
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(checkButton);


        //添加删除选中按钮
        JButton deleteButton = new JButton("删除选中的商品");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object temp = jList.getSelectedValue();
                if (temp != null) {
                    ShoppingCar.getShoppingCarInstance().removeFromShoppingCar((ProductMenu) temp);
                    jList.removeAll();
                    ProductMenu[] mealsTemp = ShoppingCar.getShoppingCarInstance().getShoppingCar();
                    jList.setListData(mealsTemp);
                    totalAmount.setText("购物车总数为：" + ShoppingCar.getShoppingCarInstance().getAmount());
                    totalPrice.setText("当前总共价格为：" + ShoppingCar.getShoppingCarInstance().getTotalPrice() + "￥");
                    new MessageView("删除购物车", "删除成功");
                } else {
                    new MessageView("删除购物车", "删除失败，请选中要删除的商品");
                }
            }
        });
        container.add(deleteButton);


        setResizable(false);
        setVisible(true);
    }


}
