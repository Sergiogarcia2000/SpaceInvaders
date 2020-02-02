package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Asteroid {

    private int x, y;
    private int lifeTime = 300;
    private int xSize = Conversor.getAdaptedResolutionWidth(48);
    private int ySize = Conversor.getAdaptedResolutionHeight(48);

    private Image asteroid_img;

    public Asteroid(int x,int y){

        this.x = x;
        this.y = y;

        try {
            asteroid_img = ImageIO.read(new File("./src/assets/asteroid.png"));
        }catch (IOException e) {
            System.out.println("Imagen del asteroide no encontrada");
        }

    }

    public Image getAsteroid_img(){ return this.asteroid_img; }

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    public void moveAsteroid(){
        int speed = 2;
        this.y += speed;
    }

    public int getLifeTime(){ return this.lifeTime; }
    public void setLifeTime(){ this.lifeTime--; }

    public int getxSize(){ return this.xSize; }
    public int getySize(){ return this.ySize; }

    public void destroy(){ this.x = 5000;}

    public Rectangle getCollisionBox(){
        return new Rectangle(this.x + Conversor.getAdaptedResolutionWidth(10), this.y + Conversor.getAdaptedResolutionWidth(10), this.xSize - Conversor.getAdaptedResolutionWidth(20), this.ySize - Conversor.getAdaptedResolutionHeight(15));
    }

}
