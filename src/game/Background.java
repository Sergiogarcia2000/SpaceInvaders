package game;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Background {

    private int speed = 1;
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

}
