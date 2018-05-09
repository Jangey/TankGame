/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 *
 * @author Jangey
 */
public class Sprites {
    TankGame game;
    protected int x, y, sprite=0, totalSprites;
    BufferedImage img;
    BufferedImage[] imgSplited = new BufferedImage[60];
    int speed = 3;
    String type;
    boolean visible = true;

    public Sprites(BufferedImage img, int initialX, int initialY, int sprite, int totalSprites, String type){
        this.totalSprites = totalSprites;
        this.sprite = sprite;
        this.type = type;
        init(img, initialX, initialY);
    }

    private void init(BufferedImage img, int initialX, int initialY) {
        game = TankGame.getInstance();
        x = initialX; y = initialY;
        this.img = img;
        int width = img.getWidth()/totalSprites;
        int height = img.getHeight();
        for(int i =0; i<=totalSprites-1; i++)
            imgSplited[i] = img.getSubimage(i*width,0,width,height);
    }

    public void draw(Graphics g, ImageObserver obs){
        g.drawImage(imgSplited[sprite], x, y, obs);
    }

    public BufferedImage getImg(){
        return imgSplited[sprite];
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public void setX(int x){
        this.x = x;
    }
    public void setY(int y){
        this.y = y;
    }
    public Rectangle getBounds(){
        return new Rectangle(x,y,img.getWidth(),img.getHeight());
    }
    public String getType(){return type;}
}
