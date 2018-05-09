/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tankgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Jangey
 */
public class TankGame extends JPanel implements Runnable{
    // the game map and two player's screen
    private BufferedImage bimg, p1view,p2view;
    private Thread thread;
    Graphics g1;
    Graphics2D g2;
    // display the health bar and default player have three lifes
    protected int width,height,lifeP1=3,lifeP2=3;
    private Background background;
    boolean gameOver = false;
    // create the array list
    protected ArrayList<Player> players;
    protected ArrayList<Sprites> sp;
    ArrayList<BreakableWall> walls2;
    ArrayList<PowerUp> powerups;
    private GameEvents gameEvents;
    KeyControl key;
    private static final TankGame game = new TankGame();
    public static HashMap<String, BufferedImage> sprites;
    private static MapBuild map;
    BufferedImage minimap;
    Point mapSize;
    public static final GameSounds sound = new GameSounds();

    private void initTankGame() {
        setFocusable(true);
        sprites = new HashMap<String, BufferedImage>();
        loadSprites();
        players = new ArrayList<>();
        sp = new ArrayList<>();
        walls2 = new ArrayList<>();
        powerups = new ArrayList<>();
        // map file to be parsed
        map = new MapBuild("map.txt");
        map.load();
        mapSize = new Point(map.w * 32, map.h * 32);
        background = new Background(mapSize.x,mapSize.y);
        background.initBackground(sprites.get("background"));
        gameEvents = new GameEvents();
        key = new KeyControl();
        addKeyListener(key);
    }
    
    public void loadSprites(){
        sprites.put("background", getSprite("Resources/Background.bmp"));
        sprites.put("player1", getSprite("Resources/Tank1.png"));
        sprites.put("player2", getSprite("Resources/Tank2.png"));
        sprites.put("wall1", getSprite("Resources/Wall1.gif"));
        sprites.put("wall2", getSprite("Resources/Wall2.gif"));
        sprites.put("bullet", getSprite("Resources/Shell(New).gif"));
        sprites.put("smallExplosion", getSprite("Resources/Explosion_small.png"));
        sprites.put("powerup", getSprite("Resources/Speedup.gif"));
        sprites.put("life", getSprite("Resources/Life.jpg"));
       
    }
    public BufferedImage getSprite(String name){
        BufferedImage img = null;
        try {
            img = ImageIO.read(getClass().getResource(name));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return img;
    }

    public class KeyControl extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            gameEvents.pressed(e,players);
        }
        public void keyReleased(KeyEvent e) {
            gameEvents.released(e, players);
        }

    }

    public void addSprites(BufferedImage img, int x, int y, int sprite, int total, String type){
        sp.add(new Sprites(img, x, y,sprite,total,type));
    }
    public void addBrWall(BufferedImage img, int x, int y){
        walls2.add(new BreakableWall(img, x, y));
    }
    public void addPlayer1(BufferedImage img, int x, int y){
        players.add(new Player(img, x, y,0,60));
    }
    public void addPlayer2(BufferedImage img, int x, int y){
        players.add(new Player(img, x, y, 30, 60));
    }
    public void addPowerUp(BufferedImage img, int x, int y) {
        powerups.add(new PowerUp(img,x,y));
    }

    @Override
    public void paint(Graphics g){
        if(bimg == null) {
            bimg = (BufferedImage) createImage(mapSize.x, mapSize.y);
            g2 = bimg.createGraphics();
            minimap = bimg;
        }
        draw();
        // Creating minimap 
        Image mini = minimap.getScaledInstance(this.getHeight() / 5,minimap.getWidth()*this.getHeight() / (5*minimap.getHeight()), BufferedImage.SCALE_FAST);
        Dimension windowSize = getSize();
        int p1x = players.get(0).getX() - windowSize.width / 4 > 0 ? players.get(0).getX() - windowSize.width / 4 : 0;
        int p1y = players.get(0).getY() - windowSize.height / 2 > 0 ? players.get(0).getY() - windowSize.height / 2 : 0;
        // Game map initializer 
        if (p1x > mapSize.x - windowSize.width / 2) {
            p1x = mapSize.x - windowSize.width / 2;
        }
        if (p1y > mapSize.y - windowSize.height) {
            p1y = mapSize.y - windowSize.height;
        }

        int p2x = players.get(1).getX() - windowSize.width / 4 > 0 ? players.get(1).getX() - windowSize.width / 4 : 0;
        int p2y = players.get(1).getY() - windowSize.height / 2 > 0 ? players.get(1).getY() - windowSize.height / 2 : 0;

        if (p2x > mapSize.x - windowSize.width / 2) {
            p2x = mapSize.x - windowSize.width / 2;
        }
        if (p2y > mapSize.y - windowSize.height) {
            p2y = mapSize.y - windowSize.height;
        }
        p1view = bimg.getSubimage(p1x, p1y, windowSize.width / 2, windowSize.height);
        p2view = bimg.getSubimage(p2x, p2y, windowSize.width / 2, windowSize.height);
        g.drawImage(p1view, 0, 0, this);
        g.drawImage(p2view, windowSize.width / 2, 0, this);
        g.drawRect(windowSize.width / 2 - 1, 0, 1, windowSize.height);
        g.drawImage(mini, windowSize.width/2-75, 550, this);
        Font f =(new Font("Courier", Font.BOLD, 25));
        g.setFont(f);
        g.setColor(Color.YELLOW);
        if(players.get(0).life>5){
        	g.setColor(Color.BLUE);
        }
        int x=0, y =0;
        g.fillRect(x+2, y-25, (int) Math.round(players.get(0).health*1.1), 20);
    
        /**
         * Display player info such as health and score.
         */
        //player 1 graphics life 
        g.setColor(Color.gray);
        g.fillRect(160,648, 50, 25);
        
        g.setColor(Color.green);
        g.fillRect(160,648, players.get(0).health * 5, 25);
        
        g.setColor(Color.white);
        g.drawRect(160, 648, 50, 25);
        
        //player 2 graphics life 
        g.setColor(Color.gray);
        g.fillRect(930,648, 50, 25);
        
        g.setColor(Color.green);
        g.fillRect(930,648, players.get(1).health * 5 , 25);
        
        g.setColor(Color.white);
        g.drawRect(930, 648, 50, 25);
        g.drawString("Player 1", x + 30, getHeight() -120);
        g.drawString("Player 2", getWidth() -200, getHeight() -120);
        
        g.drawString("Health: ", 32, getHeight() - 60);  
        g.drawString("Health: ", getWidth() - 200, getHeight() - 60);
        g.drawString("Life: " + lifeP1, 32, getHeight() - 90);
        g.drawString("Life: " + lifeP2, getWidth() - 200, getHeight() - 90);
        
        
        }
        
    private void draw(){
        background.drawBackground(g2);
        gameEvents.process(players, sp, walls2, powerups);
        ArrayList<Bullet> b;

       for(int i=0; i<sp.size();i++) {
            sp.get(i).draw(g2, this);
        }
        for(int i=0; i<walls2.size(); i++){
            walls2.get(i).draw(g2, this);
        }
        
        for(int i=0; i<powerups.size(); i++){
            powerups.get(i).draw(g2, this);
        }

        for(int i=0; i<players.size(); i++) {
            b=players.get(i).getBullets();
            for (Bullet bu : b) {
                if(bu.visible) {
                    bu.draw(g2, this);
                    bu.update();
                }
            }
            if(players.get(i).show)
                players.get(i).draw(g2, this);
        }

        for(int i=0; i<players.size(); i++){

            for (int j=0; j<players.get(i).explosions.size();j++){
                Explosion exp = players.get(i).explosions.get(j);
                if(!exp.finish()) {
                    exp.drawSmallExplosion();
                } else {
                    players.get(i).explosions.remove(exp);
                }
            }
        }
    }

    @Override
    public void run() {
        Thread me = Thread.currentThread();
        while (thread == me) {
            repaint();
            try {
                thread.sleep(25);
            } catch (InterruptedException e) {
                break;
            }

        }
    }
    // setting screen dimensions
    public void setDimensions(int w, int h) {
        this.width = w;
        this.height = h;
    }

    public static TankGame getInstance() {
        return game;
    }
    public HashMap<String, BufferedImage> getSprites() {
        return sprites;
    }

    public void start() {
        thread = new Thread(this);
        thread.setPriority(Thread.MIN_PRIORITY);
        thread.start();
    }

   
    public void gameOver() {
            gameOver = false;
            removeKeyListener(key);
            game.initTankGame();

   
    }
    
    public static void main(String argv[]) {
        final TankGame game = TankGame.getInstance();
        JFrame f = new JFrame("TankGame");
        f.addWindowListener(new WindowAdapter() {
            public void windowGainedFocus(WindowEvent e) {
                game.requestFocusInWindow();
            }
        });
        f.getContentPane().add("Center", game);
        f.pack();
        f.setSize(new Dimension(1000, 750));
        f.setLocationRelativeTo(null); // game opens in the middle of the screen
        game.setDimensions(800, 600);
        game.initTankGame();
        f.setVisible(true);
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        TankGame.sound.playLoop("Resources/Music.mid");
        game.start();
        
        
        
    }
}
