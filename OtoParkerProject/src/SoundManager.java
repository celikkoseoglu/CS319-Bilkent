import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by ASUS on 11.12.2016.
 */
public class SoundManager {
    private static boolean soundEnable =true;
    public static String INTRO_MUSIC = "intro.wav";  // Example playSound(SoundManager.INTRO_MUSIC) etc
    public static String FAIL = "fail.wav";
    public static String CHANGE = "change.wav";
    public static String SUCCESS = "success.wav";
    public static String CLICK = "tik.wav";
    public static String WEAPON = "weapon.wav";
    
    
    public SoundManager(){
    }
    public void enableSound(){
        soundEnable= true;
    }
    public void disableSound(){
        soundEnable= false;
    }
    public boolean isSoundEnable(){
        return soundEnable;
    }
    public void playSound(String filename){
        if(isSoundEnable()) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filename)));
                clip.start();
                Thread.sleep(clip.getMicrosecondLength() / 1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
