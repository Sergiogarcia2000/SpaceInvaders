package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Layout0 layout0;

    public KeyInput(Layout0 layout0){
        this.layout0 = layout0;
    }

    public void keyPressed(KeyEvent e){
        layout0.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        layout0.keyReleased(e);
    }

}
