package game.entities;

import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Background {

    private int x, y;

    private Image bg_img;

    private static List<Background> backgroundList = new ArrayList<>();

    public Background(int x, int y){

        this.x = x;
        this.y = y;

        try{
            bg_img = ImageIO.read(new File("./src/assets/sprites/background.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    public Image getImg(){
        return this.bg_img;
    }

    public static List<Background> getBackgroundList(){
        if (backgroundList.isEmpty()){
            backgroundList.add(new Background(Conversor.getAdaptedResolutionWidth(0), 0));
            backgroundList.add(new Background(Conversor.getAdaptedResolutionWidth(337), 0));
            backgroundList.add(new Background(Conversor.getAdaptedResolutionWidth(0), Conversor.getAdaptedResolutionHeight(600)));
            backgroundList.add(new Background(Conversor.getAdaptedResolutionWidth(337), Conversor.getAdaptedResolutionHeight(600)));
        }
        return backgroundList;
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
