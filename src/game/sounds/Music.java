package game.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public abstract class Music {

    private static boolean volume = true;
    private static float volumeLevel = 0.2f;
    private static Clip clip;

    public static void play(String path){

        if (volume){
            try{
                File sound = new File(path);
                clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sound));

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                volume.setValue(20f * (float) Math.log10(volumeLevel));
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static boolean getVolumeStatus(){
        return volume;
    }

    public static void toggleVolume(){
        volume = !volume;
        if (volume){
            play("./src/assets/sounds/Music.wav");
        }else{
            clip.stop();
        }
    }

}
