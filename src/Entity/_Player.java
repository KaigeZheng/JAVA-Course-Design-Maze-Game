package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


import main._Handler;
import main._Panel;
import javax.imageio.ImageIO;
import javax.swing.*;

public class _Player extends Entity {
    _Panel panel;
    _Handler handler;
    // 屏幕大小
    public final int SCREEN_X;
    public final int SCREEN_Y;
    // 游戏状态 -> 是否通关
    private boolean gameStatus = false;
    // 计时器
    private long gameTime, startTime, endTime;

    public _Player(_Panel panel, _Handler handler) {
        // 传递panel和handler -> 设定玩家初始参数 -> 初始化碰撞面积 -> 开始计时
        this.panel = panel;
        this.handler = handler;
        this.setDefaultVale();
        this.getImage();
        SCREEN_X = panel.SCREEN_WIDTH/2 - panel.TILE_SIZE;
        SCREEN_Y = panel.SCREEN_HEIGHT/2 -panel.TILE_SIZE;
        area = new Rectangle();
        area.width = 32;
        area.height = 32;
        area.x = (16 - area.width/2 >= 0)?16 - area.width/2 : 0;
        area.y = (24 - area.height/2 >= 0)?24 - area.height/2 : 0;
        setStartTime();
    }

    // 开始计时
    public void setStartTime() {
        startTime = System.currentTimeMillis();
        endTime = -1;
        gameTime = -1;
    }

    // 结束计时
    public void setEndTime() {
        endTime = System.currentTimeMillis();
        gameTime = (endTime - startTime)/1000;
    }

    // 游戏结束
    public void clearGame() {
        ImageIcon icon = new ImageIcon("/image/map/ball.png");
        String notice = "cost time = " + gameTime + " seconds.";
        String title = "Congradulations!";
        JOptionPane.showMessageDialog(null, notice, title, JOptionPane.WARNING_MESSAGE, icon);
        endGame(1);
    }

    // 游戏结束处理
    public void endGame(int mode) {
        if(mode == 1) {
            System.exit(0);
        } else if (mode == 2) {
            // TODO:restart
        }
    }

    // 获取图片
    public void getImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/image/player/up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/image/player/up2.png"));
            do1 = ImageIO.read(getClass().getResourceAsStream("/image/player/do1.png"));
            do2 = ImageIO.read(getClass().getResourceAsStream("/image/player/do2.png"));
            le1 = ImageIO.read(getClass().getResourceAsStream("/image/player/le1.png"));
            le2 = ImageIO.read(getClass().getResourceAsStream("/image/player/le2.png"));
            ri1 = ImageIO.read(getClass().getResourceAsStream("/image/player/ri1.png"));
            ri2 = ImageIO.read(getClass().getResourceAsStream("/image/player/ri2.png"));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化玩家参数
    public void setDefaultVale() {
        worldX = 7 * panel.TILE_SIZE;
        worldY = 7 * panel.TILE_SIZE;
        speed = 4;
        direction = "down";
    }

    // 更新玩家状态(移动与判断碰撞与判断游戏结束)
    public void update() {
        if (handler.upPressed || handler.downPressed || handler.leftPressed || handler.rightPressed) {
            if(handler.upPressed) {
                direction = "up";
            } else if (handler.downPressed) {
                direction = "down";
            } else if (handler.leftPressed) {
                direction = "left";
            } else if (handler.rightPressed) {
                direction = "right";
            }
            // 碰撞检测
            collision = false;
            clear = false;
            panel.check.checkTile(this);
            if(collision == false) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }
            }
            if(clear == true && gameStatus == false) {
                gameStatus = true;
                setEndTime();
                System.out.println("[Game Event]Clear the game! cost time = " + gameTime + " seconds!");
                clearGame();
               // System.out.println(gameTime);
            }

            cnt++;
            if(cnt > 8) {
                if (status == 1) {
                    status = 2;
                }
                else if (status == 2) {
                    status = 1;
                }
                cnt = 0;
            }
        }
    }

    // 绘图
    public void draw(Graphics2D g2D) {
        BufferedImage image = null;
        switch (direction) {
            case "up":
                if(status == 1) {
                    image = up1;
                } else if (status == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(status == 1) {
                    image = do1;
                } else if (status == 2) {
                    image = do2;
                }
                break;
            case "left":
                if(status == 1) {
                    image = le1;
                } else if (status == 2) {
                    image = le2;
                }
                break;
            case "right":
                if(status == 1) {
                    image = ri1;
                } else if (status == 2) {
                    image = ri2;
                }
                break;
        }
        g2D.drawImage(image, SCREEN_X, SCREEN_Y, panel.TILE_SIZE, panel.TILE_SIZE, null);
    }
}
