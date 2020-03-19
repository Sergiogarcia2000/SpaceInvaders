package game.sounds;


import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public abstract class PlaySound {

    public static void play(String path){

        try{
            File sound = new File(path);
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(sound));
            clip.start();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
