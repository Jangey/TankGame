/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Jangey
 */
public class MapBuild {
    int start;
    Integer position;
    BufferedReader level;
    String filename;
    int w, h;

    public MapBuild(String name){
        String line;
        try {
            filename = name;
            level = new BufferedReader(new InputStreamReader(getClass().getResource(filename).openStream()));
            line = level.readLine();
            w = line.length();
            h=0;
            while(line!=null){
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void load(){
        TankGame game = TankGame.getInstance();
        String line;
        try {
            level = new BufferedReader(new InputStreamReader(TankGame.class.getResource(filename).openStream()));
            line = level.readLine();
            w = line.length();
            h=0;
            while(line!=null){
                for(int i = 0, n = line.length() ; i < n ; i++) {
                    char c = line.charAt(i);

                    if(c=='1'){
                        game.addSprites(game.getSprites().get("wall1"),i*32,h*32,0,1,"wall"); //*32 = size of each img
                    }

                    if(c=='2'){
                        game.addBrWall(game.getSprites().get("wall2"), i * 32, h * 32); //*32 = size of each img
                    }

                    if(c=='3'){
                        game.addPlayer1(game.getSprites().get("player1"),i*32,h*32); //*32 = size of each img
                    }
                    if(c=='4'){
                        game.addPlayer2(game.getSprites().get("player2"),i*32,h*32); //*32 = size of each img
                    }
                    if(c=='5') {
                        game.addPowerUp(game.getSprites().get("powerup"),i*32,h*32);
                    }
                }
                h++;
                line = level.readLine();
            }
            level.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}