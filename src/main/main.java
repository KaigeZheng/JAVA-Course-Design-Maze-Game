package main;

import javax.swing.*;
import java.util.Scanner;

public class main {
    public static void main(String[] args) throws Exception {
        JFrame window = new JFrame("Final Fantasy XVII");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 关闭窗口后清除后台资源
        int mode = -1;
        if (mode == -1) {
            System.out.print("Please input the difficulty value(1~3):");
            Scanner sc = new Scanner(System.in);
            mode = sc.nextInt();
        }
        _Panel mainPanel = new _Panel(mode);

        window.add(mainPanel);
        window.pack(); // 根据游戏窗口调整window大小
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        mainPanel.startGameThread();
    }
}