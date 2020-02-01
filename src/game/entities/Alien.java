package game.entities;

import game.Conversor;
import org.omg.PortableInterceptor.DISCARDING;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Alien {

    private int x = 10, y = 10;
    private int lifeTime = 5000;
    private int xSize = 48;
    private int ySize = 48;
    private boolean direction = true;
    private boolean destroyed = false;

    private Image alienImg;

    public Alien(){

        try{
            alienImg = ImageIO.read(new File("./src/assets/alien.png"));
        }catch (IOException e){
            System.out.println("No se encontró imagen del misil");
        }

    }

    public Image getAlienImg(){
        return alienImg;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public Rectangle getCollisionBox(){
        return new Rectangle(this.x, this.y, this.xSize, this.ySize);
    }

    public void destroy(){
        this.y = -5000;
        this.x = 5000;
        destroyed = true;
    }

    public int getySize(){return this.ySize;}
    public void movement(){

        if (!destroyed) {

            if (this.x >= Conversor.getWidth() - this.xSize) {
                direction = !direction;
                this.y += ySize + 10;
            } else if (this.x <= 1) {
                direction = !direction;
                this.y += ySize + 10;
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
