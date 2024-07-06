package tile;

import main._Panel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager extends Tile {
    _Panel panel;
    public Tile[] tile;
    public int[][] map;
    public int m;
    public int MAX_COL, MAX_ROW;

    public TileManager(_Panel panel) {
        this.panel = panel;
        panel.setMode(panel.mode);
        tile = new Tile[10];
    }

    // 重置参数
    public void reload(int mode, int WORLD_MAX_COL, int WORLD_MAX_ROW) {
        m = mode;
        MAX_COL = WORLD_MAX_COL;
        MAX_ROW = WORLD_MAX_ROW;
        map = new int[MAX_COL][MAX_ROW];
        load();
        getImage();
    }

    // 加载图片
    public void getImage() {
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/image/map/grassland.png"));
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/image/map/grassland2.png"));
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/image/map/grassland3.png"));
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/image/map/grassland4.png"));
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/image/map/grass.png"));
            tile[4].collision = true;
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/image/map/water.png"));
            tile[5].collision = true;
            tile[6] = new Tile();
            tile[6].image = ImageIO.read(getClass().getResourceAsStream("/image/map/notice.png"));
            tile[7] = new Tile();
            tile[7].image = ImageIO.read(getClass().getResourceAsStream("/image/map/ball.png"));
            tile[7].clear = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 加载地图
    public void load() {
        int col = 0;
        int row = 0;
        try{
            InputStream is = null;
            // 根据难度值读取不同txt文件
            if (m == 1) is = getClass().getResourceAsStream("/maps/map_16.txt");
            else if (m == 2) is = getClass().getResourceAsStream("/maps/map_32.txt");
            else if (m == 3) is = getClass().getResourceAsStream("/maps/map_64.txt");
            // TODO:随机生成地图
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            // 读取txt文件中的内容填充map[][]
            while(col < MAX_COL && row < MAX_ROW) {
                String numbers = br.readLine();
                String[] number = numbers.split(" ");
                while(col < MAX_COL) {
                    int num = Integer.parseInt(number[col]);
                    map[col][row] = num;
                    col++;
                }
                if(col == MAX_COL) {
                    col = 0;
                    row ++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 绘图
    public void draw(Graphics2D g2D) {
        int worldCol = 0;
        int worldRow = 0;

        while(worldCol < MAX_COL && worldRow < MAX_ROW) {
            int num = map[worldCol][worldRow];

            int worldX = worldCol * panel.TILE_SIZE;
            int worldY = worldRow * panel.TILE_SIZE;

            int screenX = worldX - panel.player.worldX + panel.player.SCREEN_X;
            int screenY = worldY - panel.player.worldY + panel.player.SCREEN_Y;
            if(panel.optimize){
                // 优化选项:只绘制窗口内的图片
                if(worldX + panel.TILE_SIZE > panel.player.worldX - panel.player.SCREEN_X && worldX - 2 * panel.TILE_SIZE < panel.player.worldX + panel.player.SCREEN_X &&
                        worldY + panel.TILE_SIZE > panel.player.worldY - panel.player.SCREEN_Y && worldY < 2 * panel.player.worldY - panel.TILE_SIZE + panel.player.SCREEN_Y)
                    g2D.drawImage(tile[num].image, screenX, screenY, panel.TILE_SIZE, panel.TILE_SIZE, null);
            } else {
                // 非优化选项:全部绘制
                g2D.drawImage(tile[num].image, screenX, screenY, panel.TILE_SIZE, panel.TILE_SIZE, null);
            }
            worldCol++;
            if(worldCol == MAX_COL) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
