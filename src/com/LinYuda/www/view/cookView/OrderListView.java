package com.LinYuda.www.view.cookView;

import com.LinYuda.www.po.order.Order;
import com.LinYuda.www.po.user.CookUser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 厨师查看全部订单的界面
 */
public class OrderListView extends JFrame {
    public OrderListView(long id) {
        super("查看所有订单");
        setSize(500, 620);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        //获取厨师的所有订单
        Order[] orders = CookUser.getFullOrders(id);

        JLabel jLabel = new JLabel("全部订单数：" + orders.length);
        container.add(jLabel, BorderLayout.NORTH);

        //将所有订单信息添加到JList中
        JList jList = new JList(orders);
        jList.setSize(480, 530);
        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane, BorderLayout.CENTER);

        //添加确定按钮
        JButton jButton = new JButton("确定");
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        container.add(jButton, BorderLayout.SOUTH);

        setResizable(false);
        setVisible(true);
    }


}
