package com.LinYuda.www.view.errorView;

import javax.swing.*;
import java.awt.*;

/**
 * SQL执行错误窗口
 */
public class SQLExceptionView extends JFrame {
    public SQLExceptionView() {
        super("流异常");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        JLabel errorMessage = new JLabel("流异常，你是不是在搞BUG？");
        errorMessage.setFont(new Font("微软雅黑", Font.PLAIN, 18));
        container.add(errorMessage, BorderLayout.CENTER);

        setVisible(true);
    }
}
