package com.LinYuda.www.view.normalUserView;

import com.LinYuda.www.po.user.StudentUser;

import javax.swing.*;
import java.awt.*;

/**
 * 用户查看历史订单窗口
 */
public class HistoryOrderListView extends JFrame {
    public HistoryOrderListView(long id) {
        super("查看历史记录");
        setSize(600, 360);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());


        //创建内容主窗口
        String[] historyOrder = StudentUser.getHistoryOrder(id);
        JList jList = new JList(historyOrder);
        jList.setVisibleRowCount(20);
        JScrollPane jScrollPane = new JScrollPane(jList);
        container.add(jScrollPane, BorderLayout.CENTER);


        JButton jButton = new JButton("确定");
        container.add(jButton, BorderLayout.SOUTH);


        setResizable(false);
        setVisible(true);
    }
}
