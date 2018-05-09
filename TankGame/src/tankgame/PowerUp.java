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
public class PowerUp extends BackgoundObject {

    int timer = 350;

    // making constructor to copy image and int values
    public PowerUp(BufferedImage img, int x, int y) {
        super(img, x, y);
        setAccess(false);
        visible = true;
    }
}
