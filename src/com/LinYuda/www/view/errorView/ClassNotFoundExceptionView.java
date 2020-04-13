package com.LinYuda.www.view.errorView;

import javax.swing.*;
import java.awt.*;

/**
 * JDBC预加载异常窗口
 */
public class ClassNotFoundExceptionView extends JFrame {
    public ClassNotFoundExceptionView() {
        super("数据库加载异常");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JLabel errorMessage = new JLabel("数据库加载异常，请确认是否有安装数据库");
        errorMessage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        container.add(errorMessage, BorderLayout.CENTER);

        setVisible(true);
    }
}
