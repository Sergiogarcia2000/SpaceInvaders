package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Asteroid extends Entity{

    private int lifeTime = 300;
    private Image asteroid_img;

    public Asteroid(int x,int y){
        super(x, y, new Random().nextInt(80) + 20);

        try {
            asteroid_img = ImageIO.read(new File("./src/assets/asteroid.png"));
        }catch (IOException e) {
            System.out.println("Imagen del asteroide no encontrada");
        }

    }

    public Image getAsteroid_img(){ return this.asteroid_img; }

    public void moveAsteroid(){
        int speed = 2;
        this.y += speed;
    }

    public int getLifeTime(){ return this.lifeTime; }
    public void setLifeTime(){ this.lifeTime--; }


    public void destroy(){
        if (this.size > 20){
            this.size -= 20;
        }else{
            this.x = 5000;
        }
    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int) this.x + Conversor.getAdaptedResolutionWidth(10), (int)this.y + Conversor.getAdaptedResolutionWidth(10), this.size - Conversor.getAdaptedResolutionWidth(20), this.size - Conversor.getAdaptedResolutionHeight(15));
    }

}
