package game.structure;
import game.entities.*;
import game.sounds.PlaySound;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;

public class Board extends JPanel implements ActionListener, KeyListener{

    Background bg = new Background(Conversor.getAdaptedResolutionWidth(0), 0);
    Background bg2 = new Background(Conversor.getAdaptedResolutionWidth(337), 0);
    Background bg3 = new Background(Conversor.getAdaptedResolutionWidth(0), Conversor.getAdaptedResolutionHeight(600));
    Background bg4 = new Background(Conversor.getAdaptedResolutionWidth(337), Conversor.getAdaptedResolutionHeight(600));

    private ArrayList<Misil> misils = new ArrayList<>();
    private ArrayList<Asteroid> asteroids = new ArrayList<>();
    private ArrayList<Alien> aliens = new ArrayList<>();
    private final Set<Integer> pressed = new HashSet<>();
    private int actualTime = 0;
    private int weaveTime = 0;
    private int waves = 0;


    private boolean unpaused, gameOver;
    private boolean showCollisionBoxes = false;

    public Board() {

        unpaused = true;
        gameOver = false;
        Timer timer = new Timer(15, this);
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
        drawAlien(g2D);

        if (!unpaused) {
            drawPausedScreen(g2D);
        }else if (gameOver){
            drawGameOverScreen(g2D);
        }
    }

    private void restartGame(){
        Ship.restart();
        misils.clear();
        asteroids.clear();
        pressed.clear();
        aliens.clear();
    }

    private void drawGameOverScreen(Graphics2D g2D){
        g2D.setPaint(Color.red);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.drawString("GAME OVER", Conversor.getAdaptedResolutionWidth(250), Conversor.getHeight() / 2);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(15)));
        g2D.drawString("Press ENTER to restart", Conversor.getAdaptedResolutionWidth(260), (Conversor.getHeight() / 2) + Conversor.getAdaptedResolutionWidth(20));

    }

    private void drawPausedScreen(Graphics2D g2D){
        g2D.setPaint(Color.WHITE);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.drawString("GAME PAUSED", Conversor.getAdaptedResolutionWidth(250), Conversor.getHeight() / 2);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(15)));
        g2D.drawString("Press ESC to resume", Conversor.getAdaptedResolutionWidth(280), (Conversor.getHeight() / 2) + Conversor.getAdaptedResolutionWidth(20));
    }

    private void drawAlien(Graphics2D g2D){
        for (Alien alien : aliens) {
            g2D.drawImage(alien.getAlienImg(), (int) alien.getX(),(int) alien.getY(), 48, 48, null);

            if (showCollisionBoxes) {
                g2D.draw(alien.getCollisionBox());
            }
        }
    }

    private void drawAsteroid(Graphics2D g2D){
        for (Asteroid asteroid : asteroids) {
            g2D.drawImage(asteroid.getAsteroid_img(),(int) asteroid.getX(), (int) asteroid.getY(), asteroid.getSize(), asteroid.getSize(), null);
            //g2D.drawString(Integer.toString(asteroid.getSize()), (int)asteroid.getX(),(int) asteroid.getY());

            if (showCollisionBoxes)
                g2D.draw(asteroid.getCollisionBox());
        }
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

    private void drawShip(Graphics2D g2D){
        g2D.setPaint(Color.GREEN);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.setPaint(Color.gray);

        g2D.drawImage(Ship.getInstance().getImage(), (int)Ship.getInstance().getX(), (int)Ship.getInstance().getY(), Ship.getInstance().getSize(), Ship.getInstance().getSize(), null);
        if (showCollisionBoxes)
            g2D.draw(Ship.getInstance().getCollisionBox());
    }

    private void drawMissil(Graphics2D g2D) {
        for (Misil misil : misils){
            g2D.drawImage(misil.getImg(), (int) misil.getX(), (int) misil.getY(), misil.getSize(), misil.getSize(), null);
            if (showCollisionBoxes)
                g2D.draw(misil.getCollisionBox());
        }
    }

    private void missilTick(){

        for (Misil misil : misils){
            misil.moveMisil();
            misil.setLifeTime();
        }

        for (Misil misil : misils) {
            for (Asteroid asteroid : asteroids) {
                if (misil.getCollisionBox().intersects(asteroid.getCollisionBox())) {
                    misil.destroy();
                    asteroid.hit();
                }
            }

            for (Alien alien : aliens){
                if (misil.getCollisionBox().intersects(alien.getCollisionBox())){
                    misil.destroy();
                    alien.destroy();
                }
            }
        }

        for (int i = 0; i < misils.size(); i++){
            if (misils.get(i).getLifeTime() <= 0)
                misils.remove(misils.get(i));
        }

        //System.out.println("Misils shooted: " + misils.size());
    }
    private void shipTick(){
        Ship.getInstance().move();

        if (Ship.getInstance().getLife() <= 0){
            gameOver = true;
        }

    }

    private void asteroidTick(){

            for (Asteroid asteroid : asteroids) {
                asteroid.moveAsteroid();
                asteroid.setLifeTime();
            }

            for (int i = 0; i < asteroids.size(); i++){
                if (asteroids.get(i).getCollisionBox().intersects(Ship.getInstance().getCollisionBox())) {
                    Ship.getInstance().setLife(-1);
                    asteroids.remove(asteroids.get(i));
                }
            }

            for (int i = 0; i < asteroids.size(); i++){
                if (asteroids.get(i).getLifeTime() <= 0)
                    asteroids.remove(asteroids.get(i));
            }
    }

    private void generateAsteroid(){
        asteroids.add(new Asteroid(Conversor.getAdaptedResolutionWidth(new Random().nextInt(Conversor.getWidth() - Conversor.getAdaptedResolutionWidth(48))), Conversor.getAdaptedResolutionHeight(-48)));
    }

    private void alienTick(){
        for (Alien alien : aliens) {
            alien.movement();
            if (alien.getY() + alien.getSize() >= Ship.getInstance().getY()){
                gameOver = true;
            }
        }

    }

    private void tick(){

        missilTick();
        alienTick();
        shipTick();
        asteroidTick();

        int timeBtwWave = 25;
        int timeBtwWaves = 200;
        if (waves < 10 && weaveTime > timeBtwWave) {
            for (int i = 0; i < 3; i++) {
                generateAsteroid();
                weaveTime = 0;
            }
            waves++;
        }

        if (weaveTime > timeBtwWaves)
            waves = 0;

        actualTime++;
        weaveTime++;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (unpaused && !gameOver)
            tick();

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public void keyPressed(KeyEvent e) {

        pressed.add(e.getKeyCode());

        if (pressed.contains(KeyEvent.VK_D)) {
            Ship.getInstance().setVelX(Conversor.getAdaptedResolutionWidth(8));

        }else if (pressed.contains(KeyEvent.VK_A)) {
           Ship.getInstance().setVelX(Conversor.getAdaptedResolutionWidth(-8));
        }

        if (pressed.contains(KeyEvent.VK_ESCAPE) && !gameOver)
            unpaused = !unpaused;

        if (pressed.contains(KeyEvent.VK_ENTER) && gameOver) {
            gameOver = false;
            restartGame();
        }

        if (pressed.contains(KeyEvent.VK_K))
            generateAsteroid();

        if (pressed.contains(KeyEvent.VK_O)){

            for (int i = 0; i < 20; i++) {
                try {
                    generateAsteroid();
                } catch (Exception x) {
                    x.printStackTrace();
                }
            }
        }

        if (pressed.contains(KeyEvent.VK_H))
            showCollisionBoxes = !showCollisionBoxes;

        int timeBtwAlien = 30;
        if (pressed.contains(KeyEvent.VK_G) && actualTime > timeBtwAlien) {
            aliens.add(new Alien());
            actualTime = 0;
        }


        int timeBtwShot = 10;
        if (actualTime > timeBtwShot && !gameOver){
            if (pressed.contains(KeyEvent.VK_SPACE)){
                misils.add(new Misil(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(20), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(10)));
                actualTime = 0;
                PlaySound.play("./src/assets/shot.wav");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_A) {
            Ship.getInstance().setVelX(0);
        }

        pressed.remove(e.getKeyCode());
    }
}
