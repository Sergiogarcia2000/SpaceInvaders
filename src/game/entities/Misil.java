package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Misil extends Entity{

    private int lifeTime = 800;
    private Image misil_img;

    public Misil(double x, double y){
        super(x, y, Conversor.getAdaptedResolutionWidth(32));

        try{
            misil_img = ImageIO.read(new File("./src/assets/misil.png"));
        }catch (IOException e){
            System.out.println("No se encontró imagen del misil");
        }

    }

    public Image getImg(){
        return misil_img;
    }

    public void destroy(){ this.x = 50000;}

    public Rectangle getCollisionBox(){
        return new Rectangle((int)this.x + Conversor.getAdaptedResolutionWidth(5), (int)this.y, this.size - Conversor.getAdaptedResolutionWidth(13), this.size);
    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public void setLifeTime(){
        this.lifeTime--;
    }

    public void moveMisil(){
        int speed = 10;
        this.y -= speed;
    }

}
