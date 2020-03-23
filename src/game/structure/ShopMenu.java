package game.structure;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ShopMenu extends JPanel implements ActionListener {

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

        for (int i = 0; i < 3; i++){
            g2D.drawRect((this.getWidth() / 3) * i, 0, this.getWidth()/3, this.getY()- 10);
            g2D.drawImage(shopImages.get(i), (this.getWidth() / 3) * i + 10, 10, 64, 64, null);
        }

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        repaint();
    }
}
