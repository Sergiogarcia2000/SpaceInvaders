package game.structure;

import game.entities.Ship;
import game.settings.Life;
import game.settings.Score;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class Hud extends JPanel implements ActionListener {

    Image hudImg;

    public Hud(){
        try{
            hudImg = ImageIO.read(new File("./src/assets/sprites/UnderBar.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

        Timer timer = new Timer(15, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g){
        Graphics2D g2D = (Graphics2D) g;

        // DRAWING HUD BACKGROUND
        g2D.drawImage(hudImg, 0, Conversor.HEIGHT - 80, Conversor.WIDTH - 16, 40, null);

        // DRAW LIFES
        for (int i = 1; i < Life.getLife() + 1; i++){
            g2D.drawImage(Ship.getInstance().getImage(), Conversor.getAdaptedResolutionWidth(i * 25), Conversor.HEIGHT - 75, Ship.getInstance().getSize() - 58, Ship.getInstance().getSize() - 58, null);
        }

        // DRAW SCORE
        g2D.setColor(Color.GREEN);
        g2D.setFont(new Font("Calibri",Font.BOLD,Conversor.getAdaptedResolutionWidth(18)));
        g2D.drawString("SCORE: " + Score.getScore(),Conversor.WIDTH - Conversor.getAdaptedResolutionWidth(200),Conversor.HEIGHT - Conversor.getAdaptedResolutionHeight(38));

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
