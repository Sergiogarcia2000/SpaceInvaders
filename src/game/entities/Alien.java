package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Alien extends Entity{

    private int lifeTime = 5000;
    private boolean direction = true;
    private boolean destroyed = false;

    private Image alienImg;

    public Alien(){
        super(Conversor.getAdaptedResolutionWidth(10), Conversor.getAdaptedResolutionHeight(10),Conversor.getAdaptedResolutionWidth(48));
        try{
            alienImg = ImageIO.read(new File("./src/assets/sprites/alien.png"));
        }catch (IOException e){
            System.out.println("No se encontró imagen del misil");
        }
    }

    public Image getAlienImg(){
        return alienImg;
    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int)this.x, (int)this.y, this.size, this.size);
    }

    public void destroy(){
        this.y = -5000;
        this.x = 5000;
        destroyed = true;
    }

    public void movement(){

        if (!destroyed) {

            if (this.x >= Conversor.WIDTH - this.size) {
                direction = !direction;
                this.y += this.size;
            } else if (this.x <= 1) {
                direction = !direction;
                this.y += this.size;
            }

            int speed = 2;
            if (!direction) {
                this.x += speed;
            } else {
                this.x -= speed;
            }
        }
    }

}
