/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @author Jangey
 */
public class Background extends JPanel{
    private BufferedImage background;
    private int width, height; //width and height

    public Background(int w, int h){
        this.width = w;
        this.height = h;
    }

    public void initBackground(BufferedImage img){
        background = img;
    }
    
    public void drawBackground(Graphics g){
        int TileWidth = background.getWidth(this);
        int TileHeight = background.getHeight(this);
        int NumberX = width / TileWidth;
        int NumberY = height / TileHeight;
        for (int i = -1; i <= NumberY; i++) {
            for (int j = 0; j <= NumberX; j++) {
                g.drawImage(background, j * TileWidth, i * TileHeight, TileWidth, TileHeight, this);
            }
        }
    }
}
