package game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Misil {

    private double x, y;
    private int speed = 10;
    private int lifeTime = 800;

    private Image misil_img;

    public Misil(double x, double y){
        this.x = x;
        this.y = y;

        try{
            misil_img = ImageIO.read(new File("./src/assets/misil.png"));
        }catch (IOException e){
            System.out.println("No se encontró imagen del misil");
        }

    }

    public Image getImg(){
        return misil_img;
    }

    public double getX(){
        return this.x;
    }
    public double getY(){
        return this.y;
    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(){
        this.lifeTime--;
    }

    public void moveMisil(){
        this.y -= this.speed;
    }

}
