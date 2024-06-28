package main;

import java.awt.*;
import javax.swing.*;

import Entity._Player;
import tile.TileManager;

public class _Panel extends JPanel implements Runnable {
    final int ORIGINAL_SIZE = 16;
    final int SCALE = 3;

    public int TILE_SIZE = ORIGINAL_SIZE * SCALE;

    public final int SCREEN_MAX_COL = 16;
    public final int SCREEN_MAX_ROW = 12;

    public final int SCREEN_WIDTH = SCREEN_MAX_COL * TILE_SIZE;
    public final int SCREEN_HEIGHT = SCREEN_MAX_ROW * TILE_SIZE;

    public static int WORLD_MAX_COL = 16; // 世界大小（行）
    public static int WORLD_MAX_ROW = 16; // 世界大小（列）

    //public final int WORLD_WIDTH = WORLD_MAX_COL * TILE_SIZE;
    //public final int WORLD_HEIGHT = WORLD_MAX_ROW * TILE_SIZE;

    public final boolean optimize = false;

    public int mode;

    _Handler keyListener = new _Handler();
    public Check check = new Check(this);
    Thread gameThread;

    TileManager TM = new TileManager(this);
    public _Player player = new _Player(this, keyListener);

    //int playerX = 100;
    //int playerY = 100;
    //int speed = 4;

    int FPS = 60;

    public _Panel(int m) {
        this.mode = m;
        this.setMode(mode);
        TM.reload(m, WORLD_MAX_COL, WORLD_MAX_ROW);
        this.setPreferredSize(new Dimension(SCREEN_WIDTH,SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true); // 同时进行
        this.addKeyListener(keyListener);
        this.setFocusable(true);
        this.requestFocusInWindow(); // 请求焦点
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        System.out.println("[Game Event]Game started!");
        double drawFrequency = 1000000000/FPS;
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

    public void update() {
        player.update();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2D = (Graphics2D) g;
        TM.draw(g2D);
        //g2D.setColor(Color.WHITE);
        //g2D.fillRect(playerX, playerY, TILE_SIZE, TILE_SIZE);
        player.draw(g2D);
        g2D.dispose();
    }

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

    public int get_WORLD_MAX_COL(){
        return WORLD_MAX_COL;
    }

    public int get_WORLD_MAX_ROW(){
        return WORLD_MAX_ROW;
    }
}

