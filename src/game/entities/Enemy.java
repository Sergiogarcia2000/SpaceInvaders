package game.entities;

import game.sounds.PlaySound;
import game.structure.Conversor;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import static game.sounds.Sounds.*;

public class Enemy extends Entity {

    private int life;
    private int speed;
    private boolean direction;
    private boolean dead;
    private int readyToShot;

    private Image enemyImg;

    public Enemy() {
        super(new Random().nextDouble() * Conversor.WIDTH - 50, Conversor.getAdaptedResolutionHeight(20), Conversor.getAdaptedResolutionWidth(64));
        life = 150;
        speed = 3;
        direction = new Random().nextBoolean();
        dead = false;
        readyToShot = 0;

        try{
            enemyImg = ImageIO.read(new File("./src/assets/sprites/enemy.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public Image getEnemyImg(){
        return enemyImg;
    }

    public void hit(int life){
        this.life -= life;
        PlaySound.play(EXPLOSION.getPath());

        if (this.life <= 0){
            PlaySound.play(DIE.getPath());
            this.dead = true;
        }
    }

    public void move(){

        readyToShot++;

        if (direction){
            this.x += speed;
        }else{
            this.x -= speed;
        }

        if (this.x <= 0){
            direction = !direction;
        }else if (this.x >= Conversor.WIDTH - this.size){
            direction = !direction;
        }
    }

    public Rectangle getCollisionBox(){
        return new Rectangle((int) this.x,(int) this.y, this.size, this.size);
    }

    public boolean isReadyToShot(){
        return readyToShot > 100;
    }

    public void misilShooted(){
        this.readyToShot = 0;
    }

    public boolean isDead(){
        return this.dead;
    }

}
