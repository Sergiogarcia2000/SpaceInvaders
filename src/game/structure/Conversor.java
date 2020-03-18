package game.structure;

import java.awt.*;

public final class Conversor {

    public final static Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();

    public static int width = (int) RESOLUTION.getWidth()/2;
    public static int height = Conversor.getAdaptedResolutionHeight(600);

    public static int getAdaptedResolutionWidth(int width){

        return (int)((RESOLUTION.getWidth() * width) / 1366);
    }

    public static int getAdaptedResolutionHeight(int height){

        return (int)((RESOLUTION.getHeight() * height) / 768);
    }

    public static int getWidth(){
        return width;
    }
    public static int getHeight(){
        return height;
    }

    public static Dimension getResolution(){
        return RESOLUTION;
    }
}
