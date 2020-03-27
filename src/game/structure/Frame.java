package game.structure;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Frame extends JFrame implements KeyListener {

    Hud hud = new Hud();
    Menu sm = new Menu();

    // CREAMOS UN CONSTRUCTOR PARA EL FRAME
    public Frame(){

        this.setTitle("Space Adventures");
        this.setBounds(Conversor.getAdaptedResolutionWidth((int)Conversor.getResolution().getWidth()/4), Conversor.getAdaptedResolutionHeight((int) Conversor.getResolution().getHeight() / 10),Conversor.WIDTH, Conversor.HEIGHT);
        this.setResizable(false);

        add(Board.getInstance());
        add(hud);

        Board.getInstance().setLayout(null);
        Board.getInstance().setBounds(0, 0, Conversor.WIDTH, Conversor.HEIGHT - 80);
        hud.setBounds(0, Conversor.HEIGHT - 80, Conversor.WIDTH, 80);
        sm.setBounds(Conversor.getAdaptedResolutionWidth(250), Conversor.HEIGHT / 4, 250 ,150);

        Board.getInstance().add(sm);

        sm.setEnabled(false);
        sm.setVisible(false);
        //AÑADE EL ESCUCHADOR DE TECLAS
        addKeyListener(Board.getInstance());
        addKeyListener(this);
        addMouseListener(sm);
    }

    private void toggleShopMenu(){
        if (this.sm.isEnabled()){
            sm.setEnabled(false);
            sm.setVisible(false);
        }else{
            sm.setEnabled(true);
            sm.setVisible(true);
        }
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if (keyEvent.getKeyCode() == KeyEvent.VK_ESCAPE) {
            toggleShopMenu();
            Board.getInstance().togglePause();
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
    }
}
