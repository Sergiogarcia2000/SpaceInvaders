package game.entities;

import game.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {

    private int x, y;

    private Image bg_img;

    public Background(int x, int y){

        this.x = x;
        this.y = y;

        try{
            bg_img = ImageIO.read(new File("./src/assets/background.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Image getImg(){
        return this.bg_img;
    }

    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }

    public void moveBg(){
        int speed = 1;
        this.y += speed;

        if (this.y >= Conversor.getAdaptedResolutionHeight(600)){
            this.y = Conversor.getAdaptedResolutionHeight(-600);
        }
    }

}
