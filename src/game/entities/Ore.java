package game.entities;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class Ore extends Entity {

    private int lifeTime = 0;
    private Image ore_img;

    public Ore(double x, double y) {
        super(x, y, 28);

        try{
            ore_img = ImageIO.read(new File("./src/assets/sprites/ore.png"));
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public Image getOreImg(){
        return this.ore_img;
    }

    public void move(){
        int speed = 4;
        this.y += speed;
        lifeTime++;

        if (lifeTime > 350){
            this.x = Integer.MIN_VALUE;
            this.y = Integer.MIN_VALUE;
        }

    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int)this.x, (int)this.y, this.size, this.size);
    }

    public int getScore(){
        return new Random().nextInt(25000) + 25000;
    }

    public boolean destroy(){
        return lifeTime > 360;
    }

}
