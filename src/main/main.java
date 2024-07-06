package main;

import javax.swing.*;

public class main {
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame("Final Fantasy XVII");
        _Panel mainPanel = new _Panel();
        window.add(mainPanel);
        window.pack(); // 根据游戏窗口调整window大小
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        mainPanel.startGameThread();
    }
}



