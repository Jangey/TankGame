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
public class UnbreakableWall extends BackgoundObject{
    public UnbreakableWall(BufferedImage img, int x, int y) {
        super(img, x, y);
        setAccess(false);
        visible = true;
    }
}
