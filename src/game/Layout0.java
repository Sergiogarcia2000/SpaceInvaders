package game;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Layout0 extends JPanel implements ActionListener, KeyListener{

    Ship ship = new Ship();
    Background bg = new Background(0,0);
    Background bg2 = new Background(338,0);
    Background bg3 = new Background(674,0);

    private ArrayList<Misil> misils = new ArrayList<Misil>();
    private ArrayList<Asteroid> asteroids = new ArrayList<Asteroid>();
    private final Set<Integer> pressed = new HashSet<Integer>();
    private int timeBtwShot = 160, actualTime = 0;

    public void paintComponent(Graphics g){

        super.paintComponent(g);//heredo lo que hace paintcomponent
        setLayout(null);
        setBackground(Color.BLACK);

        addKeyListener(new KeyInput(this));

        Graphics2D g2D = (Graphics2D) g;
        g2D.drawImage(bg.getImg(), (int)bg.getX(), (int)bg.getY(), 337, 600, null);
        g2D.drawImage(bg2.getImg(), (int)bg2.getX(), (int)bg2.getY(), 337, 600, null);
        g2D.drawImage(bg3.getImg(), (int)bg3.getX(), (int)bg3.getY(), 337, 600, null);


        Timer timer = new Timer(80, this);
        timer.start();

        g2D.drawImage(ship.getImage(), (int)ship.getXpos(), (int)ship.getYpos(), 64, 64, null);



        ship.move();

        for (Misil misil : misils) {
            misil.moveMisil();
            g2D.drawImage(misil.getImg(), (int)misil.getX(), (int)misil.getY(), 32, 32, null);
            misil.setLifeTime();
            if (misil.getLifeTime() <= 0){
                misils.remove(misil);
            }
        }

        for (Asteroid asteroid : asteroids){
            g2D.drawImage(asteroid.getAsteroid_img(), asteroid.getX(), asteroid.getY(), 48, 48, null);
        }

        System.out.println("Misils shooted: " + misils.size());
        actualTime++;

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) { }

    @Override
    public synchronized void keyPressed(KeyEvent e) {

        pressed.add(e.getKeyCode());

        if (pressed.contains(KeyEvent.VK_D)) {
            ship.setVelX(0.30);

        }else if (pressed.contains(KeyEvent.VK_A)) {
           ship.setVelX(-0.30);

        }

        if (pressed.contains(KeyEvent.VK_K)){
            asteroids.add(new Asteroid(100, 100));
        }

        if (actualTime > timeBtwShot){
            if (pressed.contains(KeyEvent.VK_SPACE)){
                misils.add(new Misil(ship.getXpos(), ship.getYpos() + 14));
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
