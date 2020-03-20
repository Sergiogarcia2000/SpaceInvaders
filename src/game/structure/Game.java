package game.structure;

import game.sounds.Music;

import javax.swing.*;

public class Game {
    public static void main(String[] args){


        // INSTANCIAMOS EL FRAME
        Frame ventana = new Frame();

        Music.play("./src/assets/sounds/Music.wav");

        // SETTEAMOS CONFIGURACIONES DEL FRAME
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
}
