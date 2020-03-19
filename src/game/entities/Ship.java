package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Ship extends Entity{

    private double velX = 0;
    private int life = 3;
    private int damage = 30;
    private static int score = 1000000;

    private BufferedImage ship_img = null;
    private static Ship ship;

    public Ship(){
        super(Conversor.getAdaptedResolutionWidth((int)Conversor.RESOLUTION.getWidth() / 5), Conversor.getAdaptedResolutionHeight(475), Conversor.getAdaptedResolutionWidth(64));

        try{
            ship_img = ImageIO.read(new File("./src/assets/ship.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Ship getInstance(){
        if (ship == null){
            ship = new Ship();
            return ship;
        }else{
            return ship;
        }
    }

    public static int getScore(){
        return score;
    }
    public static void setScore(int newScore){
        score = newScore;
    }

    public static void restart(){
        ship = new Ship();
    }

    public BufferedImage getImage(){
        return ship_img;
    }

    public void setVelX(double velX){
        this.velX = velX;
    }

    public void move(){
        if (this.x + Conversor.getAdaptedResolutionWidth(64) >= Conversor.getWidth()){
            this.x -= 1;
        }else if (this.x <= 0){
            this.x += 1;
        }else{
            this.x += this.velX;
        }
    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int)this.x + Conversor.getAdaptedResolutionWidth(10), (int)this.y, this.size - Conversor.getAdaptedResolutionWidth(20), this.size);
    }

    public int getDamage(){
        return this.damage;
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
        System.out.println("Tocado, vida restante: " + this.life);
    }
}
