package game.structure;

import javax.swing.*;

public class Frame extends JFrame{

    // CREAMOS UN CONSTRUCTOR PARA EL FRAME
    public Frame(){

        this.setTitle("Space Adventures");
        this.setBounds(Conversor.getAdaptedResolutionWidth((int)Conversor.getResolution().getWidth()/4), Conversor.getAdaptedResolutionHeight((int) Conversor.getResolution().getHeight() / 10),Conversor.getWidth(), Conversor.getHeight());
        this.setResizable(false);

        //INSTANCIA DE LAYOUT

        Board board = new Board();

        add(board);

        //AÑADE EL ESCUCHADOR DE TECLAS
        addKeyListener(board);


    }


}
