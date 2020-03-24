package game.structure;

import game.settings.Damage;
import game.settings.Life;
import game.settings.Turrets;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopMenu extends JPanel implements ActionListener, MouseListener {

    Timer tm;
    List<Image> shopImages= new ArrayList<>();

    public ShopMenu(){
        tm = new Timer(15, this);
        tm.start();
        this.setOpaque(true);

        try {
           shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/life.png")));
           shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/turret.png")));
            shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/score.png")));
        }catch (IOException e) {
            System.out.println("Imagen de shop no encontrada");
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.gray);
        Graphics2D g2D = (Graphics2D) g;
        g2D.setColor(Color.black);

        int drawedCubes = 0;

        for (int i = 0; i < 3; i++){
            g2D.drawRect((this.getWidth() / 3) * i, 0, this.getWidth()/3, this.getY()- 10);
            g2D.drawImage(shopImages.get(i), (this.getWidth() / 3) * i + 10, 10, 64, 64, null);
        }

        for (int i = 0; i < 3; i++){
            for (int j = 1; j <= 3; j++){
                if (i == 0){
                    if (j <= Life.getLife() - 3){
                        g2D.fillRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 11, 11);
                    }else{
                        g2D.drawRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 10, 10);
                    }
                }else if (i == 1){
                    if (j <= Turrets.getTurrets()){
                        g2D.fillRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 11, 11);
                    }else{
                        g2D.drawRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 10, 10);
                    }
                }else{
                    if (j < Damage.getDamage() - 30){
                        g2D.fillRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 11, 11);
                    }else{
                        g2D.drawRect((((this.getWidth() / 3) * i ) + j * 13) + 10, 80, 10, 10);
                    }
                }
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        int mouseX = mouseEvent.getX();
        int mouseY = mouseEvent.getY();
        System.out.println(mouseX + " " + mouseY);
        if (mouseX < 440 && mouseX > 360 && mouseY < 340 && mouseY > 240){
            System.out.println(Life.getBaseLife());
            if (Life.getBaseLife() < 6){
                Life.setLife(1);
                Life.setBaseLife(1);
            }
        }else if (mouseX < 525 && mouseX > 440 && mouseY < 340 && mouseY > 240){
            if (Turrets.getTurrets() <= 3){
                Turrets.setTurrets(1);
            }
        }else if (mouseX < 608 && mouseX > 525 && mouseY < 340 && mouseY > 240){
            Damage.setDamage(1);
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
