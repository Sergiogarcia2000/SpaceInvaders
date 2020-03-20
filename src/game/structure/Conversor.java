package game.structure;

import java.awt.*;

public final class Conversor {

    public final static Dimension RESOLUTION = Toolkit.getDefaultToolkit().getScreenSize();

    public static final int WIDTH = (int) RESOLUTION.getWidth()/2;
    public static final int HEIGHT = Conversor.getAdaptedResolutionHeight(600);

    public static int getAdaptedResolutionWidth(int width){

        return (int)((RESOLUTION.getWidth() * width) / 1366);
    }

    public static int getAdaptedResolutionHeight(int height){

        return (int)((RESOLUTION.getHeight() * height) / 768);
    }

    public static Dimension getResolution(){
        return RESOLUTION;
    }
}
