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
public class BreakableWall extends BackgoundObject {
    int timer = 250;
    
    public BreakableWall(BufferedImage img, int x, int y) {
        super(img, x, y);
        setAccess(false);
        visible = true;
    }

    @Override
    public void draw(Graphics g, ImageObserver obs) {
        if (!visible) {
            this.timer--;
            if (this.timer < 0) {
                this.timer = 250;
                this.visible = false;   // if true will make the wall rebuild after 2.5 sec
            }
        } else {
            super.draw(g, obs);
        }
    }
}

