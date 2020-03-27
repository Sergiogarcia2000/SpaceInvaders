package game.structure;

import game.settings.Damage;
import game.settings.Life;
import game.settings.Score;
import game.settings.Turrets;
import game.sounds.Music;
import game.sounds.PlaySound;

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

import static game.sounds.Sounds.*;

public class Menu extends JPanel implements ActionListener, MouseListener {

    Timer tm;
    List<Image> shopImages= new ArrayList<>();

    public Menu(){
        tm = new Timer(15, this);
        tm.start();
        this.setOpaque(true);

        try {
           shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/life.png")));
           shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/turret.png")));
           shopImages.add(ImageIO.read(new File("./src/assets/sprites/shop_sprites/score.png")));
           shopImages.add(ImageIO.read(new File("./src/assets/settings/music.png")));
           shopImages.add(ImageIO.read(new File("./src/assets/settings/sound.png")));
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



        drawImages(g2D);
        drawPrices(g2D);
        drawBuyMenu(g2D);
    }

    private void drawImages(Graphics2D g2D) {
        g2D.fillRect(0, this.getY()/2, this.getWidth(), 1);

        for (int i = 3; i < 5; i++){
            g2D.drawImage(shopImages.get(i), (this.getWidth() / 3) * (i - 3) + 25, 112, 32, 32, null);
        }

        if (!PlaySound.getVolumeStatus()){
            g2D.setColor(Color.red);
            g2D.fillRect((this.getWidth() / 3) + 25, 125, 32, 3);
            g2D.setColor(Color.black);
        }

        if (!Music.getVolumeStatus()){
            g2D.setColor(Color.red);
            g2D.fillRect(25, 125, 32, 3);
            g2D.setColor(Color.black);
        }

        for (int i = 0; i < 3; i++){
            g2D.drawRect((this.getWidth() / 3) * i, 0, this.getWidth()/3, this.getY()- 10);
            g2D.drawImage(shopImages.get(i), (this.getWidth() / 3) * i + 10, 10, 64, 64, null);
        }
    }

    private void drawPrices(Graphics2D g2D) {
        if (Life.getBaseLife() < 6){
            g2D.drawString(Integer.toString(Life.getUpgradePrice()), 20, 70);
        }else{
            g2D.drawString("MAX ", 30, 70);
        }

        if (Turrets.getTurrets() < 3){
            g2D.drawString(Integer.toString(Turrets.getUpgradePrice()), 105, 70);
        }else {
            g2D.drawString("MAX", 113, 70);
        }

        if (Damage.getUpgrades() < 3){
            g2D.drawString(Integer.toString(Damage.getUpdatePrice()), 185, 70);
        }else{
            g2D.drawString("MAX", 197, 70);
        }
    }

    private void drawBuyMenu(Graphics2D g2D) {
        for (int i = 0; i < 3; i++){
            for (int j = 1; j <= 3; j++){
                if (i == 0){
                    if (j <= Life.getBaseLife() - 3){
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
                    if (j <= Damage.getUpgrades() ){
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
        if (this.isVisible()){
            int mouseX = mouseEvent.getX();
            int mouseY = mouseEvent.getY();

            if (mouseX < 440 && mouseX > 360 && mouseY < 340 && mouseY > 240){
                if (Life.getBaseLife() < 6 && Score.getScore() > Life.getUpgradePrice()){
                    Score.setScore(-Life.getUpgradePrice());
                    Life.setBaseLife(1);
                    PlaySound.play(POWERUP.getPath());
                }
            }else if (mouseX < 525 && mouseX > 440 && mouseY < 340 && mouseY > 240){
                if (Turrets.getTurrets() <= 3 && Score.getScore() > Turrets.getUpgradePrice()){
                    Score.setScore(-Turrets.getUpgradePrice());
                    Turrets.setTurrets(1);
                    PlaySound.play(POWERUP.getPath());
                }
            }else if (mouseX < 608 && mouseX > 525 && mouseY < 340 && mouseY > 240){
                if (Damage.getUpgrades() < 3 && Score.getScore() > Damage.getUpdatePrice()){
                    Score.setScore(-Damage.getUpdatePrice());
                    Damage.setDamage(10);
                    Damage.setUpgrades(1);
                    PlaySound.play(POWERUP.getPath());
                }
            }else if (mouseX > 360 && mouseY > 347 && mouseX < 440 && mouseY < 390){
                Music.toggleVolume();
            }else if (mouseX > 443 && mouseY > 347 && mouseX < 524 && mouseY < 390){
                PlaySound.toggleVolume();
            }
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
