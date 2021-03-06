package game.sounds;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

public abstract class PlaySound {

    private static boolean volume = true;

    public static void play(String path){

        if (volume){
            try{
                File sound = new File(path);
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(sound));

                FloatControl volume = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);

                volume.setValue(20f * (float) Math.log10(0.5));

                clip.start();

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
    }
}
