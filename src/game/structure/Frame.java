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
        Hud hud = new Hud();

        add(board);
        add(hud);

        board.setBounds(0, 0, Conversor.getWidth(), Conversor.getHeight() - 80);
        hud.setBounds(0, Conversor.getHeight() - 80, Conversor.getWidth(), 80);

        //AÑADE EL ESCUCHADOR DE TECLAS
        addKeyListener(board);


    }


}
