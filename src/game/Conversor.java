package game;

import java.awt.*;

public final class Conversor {

    private static Dimension res = Toolkit.getDefaultToolkit().getScreenSize();

    public static int width = (int)res.getWidth()/2;
    public static int height = Conversor.getAdaptedResolutionHeight(600);

    public static int getAdaptedResolutionWidth(int width){

        return (int)((res.getWidth() * width) / 1366);
    }

    public static int getAdaptedResolutionHeight(int height){

        return (int)((res.getHeight() * height) / 768);
    }

    public static int getWidth(){
        return width;
    }
    public static int getHeight(){
        return height;
    }

    public static Dimension getResolution(){
        return res;
    }
}
