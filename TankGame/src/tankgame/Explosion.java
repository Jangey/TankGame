/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.awt.image.BufferedImage;

/**
 *
 * @author Jangey
 */
public class Explosion extends Sprites {
    int delay, now;

    public Explosion(BufferedImage img, int initialX, int initialY, int sprite, int totalSprites) {
        super(img, initialX, initialY, sprite, totalSprites, "explosion");
     
        delay = 3;
        now = 0;
    }
    /**
     * to display explosion when bullet hit wall
     */
    
    public void drawSmallExplosion(){
            draw(game.g2, game);
            now++;
            if(now>delay){
                now=0;
                sprite++;
            }
    }
    
    // display explosion when the player's tank dead
    
    public void deathExplosion(){
         draw(game.g2, game);
            now++;
            if(now>delay){
                now=0;
                sprite++;
            }
    }
    
    public boolean finish(){
        return sprite >= totalSprites;
    }
}

