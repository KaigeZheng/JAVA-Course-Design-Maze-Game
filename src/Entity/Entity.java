package Entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;
    BufferedImage up1, up2, do1, do2, le1, le2, ri1, ri2;
    public String direction;
    int cnt = 0;
    int status = 1;
    public Rectangle area;
    public boolean collision;
    public boolean clear = false;
}

