package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Asteroid {

    private int x, y;
    private int speed = 2;

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

    public Image getAsteroid_img(){
        return this.asteroid_img;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

}
