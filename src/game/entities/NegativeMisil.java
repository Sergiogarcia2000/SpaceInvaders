package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class NegativeMisil extends Entity{

    private Image misilImg;
    private int lifeTime;

    public NegativeMisil(int x, int y){
        super(x, y, Conversor.getAdaptedResolutionWidth(32));
        lifeTime = 0;
        try{
            misilImg = ImageIO.read(new File("./src/assets/sprites/enemyMisil.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Image getMisilImg(){
        return misilImg;
    }

    public void destroy(){
        this.x = Integer.MIN_VALUE;
        this.y = Integer.MIN_VALUE;
    }

    public void move(){
        this.y += 5;
        lifeTime++;
    }

    public int getLifeTime(){
        return this.lifeTime;
    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int) this.x, (int) this.y, this.size, this.size);
    }

}
