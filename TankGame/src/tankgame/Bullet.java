/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author Jangey
 */
public class Bullet{
    int rotation,x,y;
    BufferedImage b;
    boolean visible;
    public Bullet(BufferedImage img, int initialX, int initialY, int rotation) {
        this.x = initialX;
        b=img;
        this.y=initialY;
        this.rotation = rotation;
        visible = true;
    }

    public void draw(Graphics g, ImageObserver obs){
        g.drawImage(b, x, y, obs);
    }
    public void update(){
        x+=Math.cos(Math.toRadians(rotation))*5;
        y-=Math.sin(Math.toRadians(rotation))*5;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public BufferedImage getImg(){
        return b;
    }
}
