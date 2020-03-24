package game.entities;

import game.sounds.PlaySound;
import game.sounds.Sounds;
import game.structure.Conversor;

import javax.imageio.ImageIO;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Ship extends Entity{

    private double velX = 0;

    private BufferedImage ship_img = null;
    private static Ship ship;

    public Ship(){
        super(Conversor.getAdaptedResolutionWidth((int)Conversor.RESOLUTION.getWidth() / 5), Conversor.getAdaptedResolutionHeight(475), Conversor.getAdaptedResolutionWidth(64));

        try{
            ship_img = ImageIO.read(new File("./src/assets/sprites/ship.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static Ship getInstance(){
        if (ship == null){
            ship = new Ship();
        }
        return ship;
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
        if (this.x + Conversor.getAdaptedResolutionWidth(64) >= Conversor.WIDTH){
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
}
