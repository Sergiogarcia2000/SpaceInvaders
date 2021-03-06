package game.structure;

import game.entities.*;
import game.settings.Damage;
import game.settings.Life;
import game.settings.Score;
import game.settings.Turrets;
import game.sounds.PlaySound;

import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.List;

import static game.sounds.Sounds.*;

public class Board extends JPanel implements ActionListener, KeyListener{



    private List<Laser> lasers = new ArrayList<>();
    private List<Asteroid> asteroids = new ArrayList<>();
    private List<Alien> aliens = new ArrayList<>();
    private List<Ore> ores = new ArrayList<>();
    private List<Enemy> enemies = new ArrayList<>();
    private List<NegativeMisil> misils = new ArrayList<>();

    private final Set<Integer> pressed = new HashSet<>();
    private int actualTime = 0;
    private int weaveTime = 0;
    private int waves = 0;

    private boolean unpaused, gameOver;
    private boolean showCollisionBoxes = false;

    private int asteroidRounds = 0;

    private static Board board;

    public Board() {

        unpaused = true;
        gameOver = false;
        Timer timer = new Timer(15, this);
        timer.start();
    }

    public static Board getInstance(){
        if (board == null){
            board = new Board();
        }
        return board;
    }

    public void togglePause(){
        this.unpaused = !this.unpaused;
    }

    @Override
    public void paintComponent(Graphics g){

        super.paintComponent(g);
        setLayout(null);
        setBackground(Color.BLACK);
        addKeyListener(new KeyInput(this));
        Graphics2D g2D = (Graphics2D) g;

        drawBackground(g2D);
        drawShip(g2D);
        drawLaser(g2D);
        drawAsteroid(g2D);
        drawAlien(g2D);
        drawOre(g2D);
        drawEnemiy(g2D);
        drawNegativeMisil(g2D);

        if (!unpaused) {
            drawPausedScreen(g2D);
        }else if (gameOver){
            drawGameOverScreen(g2D);
        }
    }

    private void restartGame(){
        PlaySound.play(DIE.getPath());

        Ship.restart();
        Life.restartLife();
        lasers.clear();
        asteroids.clear();
        pressed.clear();
        aliens.clear();
    }

    private void drawGameOverScreen(Graphics2D g2D){
        g2D.setPaint(Color.red);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.drawString("GAME OVER", Conversor.getAdaptedResolutionWidth(250), Conversor.HEIGHT / 2);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(15)));
        g2D.drawString("Press ENTER to restart", Conversor.getAdaptedResolutionWidth(260), (Conversor.WIDTH / 2) + Conversor.getAdaptedResolutionWidth(20));

    }

    private void drawPausedScreen(Graphics2D g2D){
        g2D.setPaint(Color.WHITE);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.drawString("GAME PAUSED", Conversor.getAdaptedResolutionWidth(250), Conversor.HEIGHT / 2);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(15)));
        g2D.drawString("Press ESC to resume", Conversor.getAdaptedResolutionWidth(280), (Conversor.HEIGHT / 2) + Conversor.getAdaptedResolutionWidth(20));
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

    private void drawEnemiy(Graphics2D g2D){
        for (Enemy enemy : enemies){
            g2D.drawImage(enemy.getEnemyImg(), (int) enemy.getX(), (int) enemy.getY(), enemy.getSize(), enemy.getSize(), null);
        }
    }

    private void drawNegativeMisil(Graphics2D g2D){
        for (NegativeMisil misil : misils){
            g2D.drawImage(misil.getMisilImg(), (int) misil.getX(),(int) misil.getY(), misil.getSize(), misil.getSize(),  null);
        }
    }

    private void drawOre(Graphics2D g2D){
        for (Ore ore : ores){
            g2D.drawImage(ore.getOreImg(),(int) ore.getX(),(int) ore.getY(), ore.getSize(), ore.getSize(), null);

            if (showCollisionBoxes){
                g2D.draw(ore.getCollisionBox());
            }
        }
    }

    private void drawBackground(Graphics2D g2D){
        int bgWidth = Conversor.getAdaptedResolutionWidth(337);
        int bgHeight = Conversor.getAdaptedResolutionHeight(600);

        List<Background> bgList = Background.getBackgroundList();

        for (Background bg : bgList){
            g2D.drawImage(bg.getImg(), bg.getX(), bg.getY(), bgWidth, bgHeight, null);
            bg.moveBg();
        }
    }

    private void drawShip(Graphics2D g2D){
        g2D.setPaint(Color.GREEN);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(30)));
        g2D.setPaint(Color.gray);

        g2D.drawImage(Ship.getInstance().getImage(), (int)Ship.getInstance().getX(), (int)Ship.getInstance().getY(), Ship.getInstance().getSize(), Ship.getInstance().getSize(), null);
        if (showCollisionBoxes)
            g2D.draw(Ship.getInstance().getCollisionBox());
    }

    private void drawLaser(Graphics2D g2D) {
        for (Laser laser : lasers){
            g2D.drawImage(laser.getImg(), (int) laser.getX(), (int) laser.getY(), laser.getSize(), laser.getSize(), null);
            if (showCollisionBoxes)
                g2D.draw(laser.getCollisionBox());
        }
    }

    private void laserTick(){

        for (Laser laser : lasers){
            laser.moveMisil();
            laser.setLifeTime();
        }

        for (Laser laser : lasers){
            for (Enemy enemy : enemies){

                if (laser.getCollisionBox().intersects(enemy.getCollisionBox())){
                    laser.destroy();
                    enemy.hit(Damage.getDamage());
                }
            }
        }

        for (Laser laser : lasers) {
            for (Asteroid asteroid : asteroids) {
                if (laser.getCollisionBox().intersects(asteroid.getCollisionBox())) {
                    laser.destroy();
                    asteroid.hit();
                    int chance = new Random().nextInt(100);

                    if (chance < 1){
                        ores.add(new Ore(asteroid.getX() + (asteroid.getSize() / 2), asteroid.getY() + (asteroid.getSize() / 2)));
                    }
                }
            }

            for (Alien alien : aliens){
                if (laser.getCollisionBox().intersects(alien.getCollisionBox())){
                    laser.destroy();
                    alien.destroy();
                }
            }
        }

        for (int i = 0; i < lasers.size(); i++){
            if (lasers.get(i).getLifeTime() <= 0)
                lasers.remove(lasers.get(i));
        }
    }

    private void negativeMisilTick(){
        for (NegativeMisil misil : misils){
            misil.move();
            if (misil.getCollisionBox().intersects(Ship.getInstance().getCollisionBox())){
                misil.destroy();
                Life.setLife(-1);
            }
        }
    }

    private void oreTick(){

        for(int i = 0; i < ores.size(); i++){
            ores.get(i).move();

            if (ores.get(i).destroy()){
                ores.remove(ores.get(i));
            }
        }
    }

    private void shipTick(){
        Ship.getInstance().move();

        if (Life.getLife() <= 0){
            gameOver = true;
        }

        for (int i = 0; i < ores.size(); i++){

            if (ores.get(i).getCollisionBox().intersects(Ship.getInstance().getCollisionBox())){
                Score.setScore(ores.get(i).getScore());
                ores.remove(ores.get(i));
                PlaySound.play(ORE.getPath());
            }
        }
    }

    private void asteroidTick(){

            for (Asteroid asteroid : asteroids) {
                asteroid.moveAsteroid();
                asteroid.setLifeTime();
            }

            for (int i = 0; i < asteroids.size(); i++){
                if (asteroids.get(i).getCollisionBox().intersects(Ship.getInstance().getCollisionBox())) {
                    Life.setLife(-1);
                    asteroids.remove(asteroids.get(i));
                }
            }

            for (int i = 0; i < asteroids.size(); i++){
                if (asteroids.get(i).getLifeTime() <= 0)
                    asteroids.remove(asteroids.get(i));
            }
    }

    private void generateAsteroid(){
        asteroids.add(new Asteroid(Conversor.getAdaptedResolutionWidth(new Random().nextInt(Conversor.WIDTH - Conversor.getAdaptedResolutionWidth(48))), Conversor.getAdaptedResolutionHeight(-48)));
    }

    private void alienTick(){
        for (Alien alien : aliens) {
            alien.movement();
            if (alien.getY() + alien.getSize() >= Ship.getInstance().getY()){
                gameOver = true;
            }
        }

    }

    private void enemyTick(){
        if (asteroidRounds / 10 >= 5){
            enemies.add(new Enemy());
            asteroidRounds = 0;
        }

        for(int i = 0; i < enemies.size(); i++){
            if (enemies.get(i).isReadyToShot()){
                misils.add(new NegativeMisil((int) enemies.get(i).getX(),(int) enemies.get(i).getY()));
                enemies.get(i).misilShooted();
            }

            if (enemies.get(i).isDead()){
                enemies.remove(enemies.get(i));
            }else{
                enemies.get(i).move();
            }
        }

    }

    private void tick(){


        laserTick();
        alienTick();
        shipTick();
        asteroidTick();
        oreTick();
        enemyTick();
        negativeMisilTick();

        int timeBtwWave = 25;
        int timeBtwWaves = 200;
        if (waves < 10 && weaveTime > timeBtwWave) {
            for (int i = 0; i < 3; i++) {
                generateAsteroid();
                weaveTime = 0;
            }
            waves++;
            asteroidRounds++;
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

        if (pressed.contains(KeyEvent.VK_ENTER) && gameOver) {
            gameOver = false;
            restartGame();
        }

        if (pressed.contains(KeyEvent.VK_K))
            generateAsteroid();

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
                actualTime = 0;
                if (Turrets.getTurrets() == 1){
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(17), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(10)));
                    PlaySound.play(LASER.getPath());
                }else if (Turrets.getTurrets() == 2){
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(5), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(5)));
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(30), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(5)));
                    PlaySound.play(LASER.getPath());
                    PlaySound.play(LASER.getPath());
                }else{
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(5), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(5)));
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(30), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(5)));
                    lasers.add(new Laser(Ship.getInstance().getX() + Conversor.getAdaptedResolutionWidth(17), Ship.getInstance().getY() - Conversor.getAdaptedResolutionHeight(10)));
                    PlaySound.play(LASER.getPath());
                    PlaySound.play(LASER.getPath());
                    PlaySound.play(LASER.getPath());
                }
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
