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
public class BackgoundObject {
    boolean accessible;
    int x, y;
    BufferedImage img;
    boolean visible;

    public BackgoundObject(BufferedImage img, int x, int y){
        this.x = x;
        this.y = y;
        this.img = img;
        visible = true;
    }

    public void draw(Graphics g, ImageObserver obs){
      g.drawImage(img,x,y,obs);
    }

    public boolean getAccess(){
        return accessible;
    }
    public void setAccess(boolean value){
        accessible = value;
    }
    public int getX(){return x;}
    public int getY(){return y;}
    public BufferedImage getImg(){return img;}
}

