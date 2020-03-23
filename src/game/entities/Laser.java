package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Laser extends Entity{

    private int lifeTime = 800;

    private int imageFps = 0;
    private ArrayList<Image> images = new ArrayList<>();

    public Laser(double x, double y){
        super(x, y, Conversor.getAdaptedResolutionWidth(32));

        try{
            for (int i = 1; i < 4; i++){
                images.add(ImageIO.read(new File("./src/assets/sprites/laser_anim/LaserAnim" + i + ".png")));
            }
        }catch (IOException e){
            System.out.println("No se encontró imagen del misil");
        }
    }

    public Image getImg(){
        if (imageFps <= 15){
            imageFps++;
            return images.get(0);
        }else if (imageFps <= 30){
            imageFps++;
            return images.get(1);
        }else if (imageFps <= 45){
            imageFps++;
            return images.get(2);
        }else{
            imageFps = 0;
            return images.get(1);
        }

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
