package game.structure;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

    Board board;

    public KeyInput(Board board){
        this.board = board;
    }

    public void keyPressed(KeyEvent e){
        board.keyPressed(e);
    }

    public void keyReleased(KeyEvent e){
        board.keyReleased(e);
    }

}
