package game;
import game.entities.Asteroid;
import game.entities.Background;
import game.entities.Misil;
import game.entities.Ship;
import game.structure.KeyInput;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Board extends JPanel implements ActionListener, KeyListener{

    Ship ship;
    Background bg = new Background(Conversor.getAdaptedResolutionWidth(0), 0);
    Background bg2 = new Background(Conversor.getAdaptedResolutionWidth(337), 0);
    Background bg3 = new Background(Conversor.getAdaptedResolutionWidth(0), Conversor.getAdaptedResolutionHeight(600));
    Background bg4 = new Background(Conversor.getAdaptedResolutionWidth(337), Conversor.getAdaptedResolutionHeight(600));

    private ArrayList<Misil> misils = new ArrayList<>();
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private final Set<Integer> pressed = new HashSet<>();
    private int actualTime = 0;

    public Board(){
        ship = new Ship();

        Timer timer = new Timer(17, this);
        timer.start();
    }

    public void paintComponent(Graphics g){

        super.paintComponent(g);//heredo lo que hace paintcomponent
        setLayout(null);
        setBackground(Color.BLACK);
        addKeyListener(new KeyInput(this));
        Graphics2D g2D = (Graphics2D) g;

        drawBackground(g2D);
        drawShip(g2D);
        drawMissil(g2D);
        drawAsteroid(g2D);

        actualTime++;
        //System.out.println(actualTime);
    }

    private void drawAsteroid(Graphics2D g2D){
        for (Asteroid asteroid : asteroids)
            g2D.drawImage(asteroid.getAsteroid_img(), asteroid.getX(), asteroid.getY(), 48, 48, null);
    }

    private void drawBackground(Graphics2D g2D){
        int bgWidth = Conversor.getAdaptedResolutionWidth(337);
        int bgHeight = Conversor.getAdaptedResolutionHeight(600);
        g2D.drawImage(bg.getImg(), bg.getX(), bg.getY(), bgWidth, bgHeight, null);
        g2D.drawImage(bg2.getImg(), bg2.getX(), bg2.getY(),bgWidth, bgHeight, null);
        g2D.drawImage(bg3.getImg(), bg3.getX(), bg3.getY(),bgWidth, bgHeight, null);
        g2D.drawImage(bg4.getImg(), bg4.getX(), bg4.getY(),bgWidth, bgHeight, null);

        bg.moveBg();
        bg2.moveBg();
        bg3.moveBg();
        bg4.moveBg();
    }

    private void drawShip(Graphics2D g2D){ g2D.drawImage(ship.getImage(), (int)ship.getXpos(), (int)ship.getYpos(), Conversor.getAdaptedResolutionWidth(64), Conversor.getAdaptedResolutionHeight(64), null); }

    private void drawMissil(Graphics2D g2D){
        for (Misil misil : misils)
            g2D.drawImage(misil.getImg(), (int)misil.getX(), (int)misil.getY(), Conversor.getAdaptedResolutionWidth(32), Conversor.getAdaptedResolutionHeight(32), null);
    }

    private void missilTick(){

        for (Misil misil : misils){
            misil.moveMisil();
            misil.setLifeTime();
            if (misil.getLifeTime() <= 0)
                misils.remove(misil);
        }

        //System.out.println("Misils shooted: " + misils.size());
    }
    private void shipTick(){ ship.move();}
    private void asteroidTick(){

            for (Asteroid asteroid : asteroids) {
                asteroid.moveAsteroid();
                asteroid.setLifeTime();
            }

            for (int i = 0; i < asteroids.size(); i++){
                if (asteroids.get(i).getLifeTime() <= 0)
                    asteroids.remove(asteroids.get(i));
            }

        System.out.println("Asteroids: " + asteroids.size());
    }

    private void tick(){

        missilTick();
        shipTick();
        asteroidTick();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        tick();
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        pressed.add(e.getKeyCode());

        if (pressed.contains(KeyEvent.VK_D)) {
            ship.setVelX(Conversor.getAdaptedResolutionWidth(8));

        }else if (pressed.contains(KeyEvent.VK_A)) {
           ship.setVelX(Conversor.getAdaptedResolutionWidth(-8));
        }

        if (pressed.contains(KeyEvent.VK_K)){
            asteroids.add(new Asteroid(Conversor.getAdaptedResolutionWidth(new Random().nextInt(Conversor.getWidth() - Conversor.getAdaptedResolutionWidth(48))), Conversor.getAdaptedResolutionHeight(-48)));
        }

        int timeBtwShot =25;
        if (actualTime > timeBtwShot){
            if (pressed.contains(KeyEvent.VK_SPACE)){
                misils.add(new Misil(ship.getXpos() + Conversor.getAdaptedResolutionWidth(20), ship.getYpos() - Conversor.getAdaptedResolutionHeight(10)));
                actualTime = 0;
            }
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
            ship.setVelX(0);
        }

        pressed.remove(e.getKeyCode());
    }
}
