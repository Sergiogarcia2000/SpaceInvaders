package game;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends JPanel{


    private double velX = 0;
    private double x;
    private double y;
    private int life = 3;

    private BufferedImage ship_img = null;


    public Ship(){

        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();

        x = screen.getWidth()/5;
        y = screen.getHeight() / 2.3;

        try{
            ship_img = ImageIO.read(new File("./src/assets/ship.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public BufferedImage getImage(){
        return ship_img;
    }

    public void setImage(BufferedImage img){
        this.ship_img = img;
    }

    public double getXpos(){
        return this.x;
    }

    public double getYpos(){
        return this.y;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }


    public void move(){
        this.x += this.velX;
    }


    public int getLife(){
        return this.life;
    }

    /*
    * SETTER PARA CAMBIAR LA VIDA
    * @param NUM: cantidad de vida que se va a sumar o restar
     */
    public void setLife(int num){
        this.life += num;
    }
}
