package main;

import java.awt.event.*;

public class _Handler implements KeyListener {
    public boolean upPressed, downPressed, leftPressed, rightPressed;


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = true;
            System.out.println("[Player Move]W");
        }
        if(code == KeyEvent.VK_S) {
            downPressed = true;
            System.out.println("[Player Move]S");
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = true;
            System.out.println("[Player Move]A");
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = true;
            System.out.println("[Player Move]D");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if(code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if(code == KeyEvent.VK_A) {
            leftPressed = false;
        }
        if(code == KeyEvent.VK_D) {
            rightPressed = false;
        }
    }
}
