package main;

import java.awt.*;
import javax.swing.*;

import Entity._Player;
import tile.TileManager;

public class _Panel extends JPanel implements Runnable {
    final int ORIGINAL_SIZE = 16;
    final int SCALE = 3;
    public int TILE_SIZE = ORIGINAL_SIZE * SCALE; // 图形单元: 48 * 48像素的图片
    // 屏幕显示大小
    public final int SCREEN_MAX_COL = 16;
    public final int SCREEN_MAX_ROW = 12;
    public final int SCREEN_WIDTH = SCREEN_MAX_COL * TILE_SIZE;
    public final int SCREEN_HEIGHT = SCREEN_MAX_ROW * TILE_SIZE;
    //世界大小
    public static int WORLD_MAX_COL = 16; // 世界大小（行）
    public static int WORLD_MAX_ROW = 16; // 世界大小（列）
    // 优化选项, 若改为true可以限制实际画图的规模 #TODO:暂未对用户开放optimize的设置
    public final boolean optimize = false;
    // 难度
    public int mode = -1;
    // 事件监听器
    _Handler keyListener = new _Handler();
    // 碰撞检测器
    public Check check = new Check(this);
    // 地图刷新管理器
    TileManager TM = new TileManager(this);
    //玩家类
    public _Player player = new _Player(this, keyListener);
    // 游戏主线程
    Thread gameThread;

    public _Panel() {
        // 设置难度 -> 根据难度再初始化变量 -> 设置窗口尺寸 -> 设置监听器
        mode = setDifficulty();
        this.setMode(mode);
        TM.reload(mode, WORLD_MAX_COL, WORLD_MAX_ROW);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // 同时进行
        this.setFocusable(true);
        this.requestFocusInWindow(); // 请求焦点
        this.addKeyListener(keyListener);
    }

    // 设置难度
    public int setDifficulty() {
        ImageIcon icon = new ImageIcon("/image/map/ball.png"); // #TODO:载图有问题
        String[] options = {"1", "2", "3"};
        return JOptionPane.showOptionDialog(null, "Please choose the difficulty", "Difficulty Option",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[1]) + 1;
        // #TODO:应该处理异常的
    }

    // 开始游戏线程
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    // run
    @Override
    public void run() {
        System.out.println("[Game Event]Game started!");
        int FPS = 60;
        double drawFrequency = (double) 1000000000 / FPS;
        double nextDrawTime = System.nanoTime() + drawFrequency;

        while(gameThread != null) {
            //System.out.print("Game started!");
            update();
            repaint();
            try{
                double remainTime = nextDrawTime - System.nanoTime();
                remainTime = remainTime/1000000;
                if(remainTime < 0) {
                    remainTime = 0;
                }
                Thread.sleep((long)remainTime);
                nextDrawTime = nextDrawTime + drawFrequency;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 更新图形状态
    public void update() {
        //draw();
        player.update();
    }

    // 绘图
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        TM.draw(g2D);
        player.draw(g2D);
        g2D.dispose();
    }

    // 再初始化参数
    public void setMode(int m) {
        if (m == 1) {
            this.WORLD_MAX_COL = 16;
            this.WORLD_MAX_ROW = 16;
        } else if (m == 2) {
            this.WORLD_MAX_COL = 32;
            this.WORLD_MAX_ROW = 32;
        } else if (m == 3) {
            this.WORLD_MAX_COL = 64;
            this.WORLD_MAX_ROW = 64;
        }
    }
}

