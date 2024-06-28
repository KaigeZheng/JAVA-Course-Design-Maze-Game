package main;

import Entity.Entity;

public class Check {
    _Panel panel;

    public Check(_Panel panel) {
        this.panel = panel;
    }

    public void checkTile(Entity e) {
        int eLeftWorldX = e.worldX + e.area.x;
        int eRightWorldX = e.worldX + e.area.x + e.area.width;
        int eTopWorldY = e.worldY + e.area.y;
        int eBotWorldY = e.worldY + e.area.y + e.area.height;

        int eLeftCol = eLeftWorldX/panel.TILE_SIZE;
        int eRightCol = eRightWorldX/panel.TILE_SIZE;
        int eTopRow = eTopWorldY/panel.TILE_SIZE;
        int eBotRow = eBotWorldY/panel.TILE_SIZE;

        int tileNum1, tileNum2;

        switch (e.direction) {
            case "up":
                eTopRow = (eTopWorldY - e.speed) / panel.TILE_SIZE;
                tileNum1 = panel.TM.map[eLeftCol][eTopRow];
                tileNum2 = panel.TM.map[eRightCol][eTopRow];
                if(panel.TM.tile[tileNum1].collision || panel.TM.tile[tileNum2].collision) {
                    e.collision = true;
                }
                if(panel.TM.tile[tileNum1].clear || panel.TM.tile[tileNum2].clear) {
                    e.clear = true;
                }
                break;
            case "down":
                eBotRow = (eBotWorldY + e.speed) / panel.TILE_SIZE;
                tileNum1 = panel.TM.map[eLeftCol][eBotRow];
                tileNum2 = panel.TM.map[eRightCol][eBotRow];
                if(panel.TM.tile[tileNum1].collision || panel.TM.tile[tileNum2].collision) {
                    e.collision = true;
                }
                if(panel.TM.tile[tileNum1].clear || panel.TM.tile[tileNum2].clear) {
                    e.clear = true;
                }
                break;
            case "left":
                eLeftCol = (eLeftWorldX - e.speed) / panel.TILE_SIZE;
                tileNum1 = panel.TM.map[eLeftCol][eTopRow];
                tileNum2 = panel.TM.map[eLeftCol][eBotRow];
                if(panel.TM.tile[tileNum1].collision || panel.TM.tile[tileNum2].collision) {
                    e.collision = true;
                }
                if(panel.TM.tile[tileNum1].clear || panel.TM.tile[tileNum2].clear) {
                    e.clear = true;
                }
                break;
            case "right":
                eRightCol = (eRightWorldX + e.speed) / panel.TILE_SIZE;
                tileNum1 = panel.TM.map[eRightCol][eTopRow];
                tileNum2 = panel.TM.map[eRightCol][eBotRow];
                if(panel.TM.tile[tileNum1].collision || panel.TM.tile[tileNum2].collision) {
                    e.collision = true;
                }
                if(panel.TM.tile[tileNum1].clear || panel.TM.tile[tileNum2].clear) {
                    e.clear = true;
                }
                break;
        }
    }
}
