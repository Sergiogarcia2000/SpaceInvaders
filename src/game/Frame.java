package game;

import java.awt.*;
import javax.swing.*;

public class Frame extends JFrame{

    public int width = 800;
    public int heigth = 600;

    // CREAMOS UN CONSTRUCTOR PARA EL FRAME
    public Frame(){
        Dimension res = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("Space Adventures");
        this.setBounds((int)res.getWidth()/4, (int)res.getHeight() / 4,width, heigth);
        this.setResizable(false);

        //INSTANCIA DE LAYOUT

        Layout0 layout0 = new Layout0();

        add(layout0);

        //AÑADE EL ESCUCHADOR DE TECLAS
        addKeyListener(layout0);


    }


}
