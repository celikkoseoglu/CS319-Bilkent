package ViewManagement;

import javax.sound.sampled.*;
import java.io.File;

/**
 * Created by ASUS on 11.12.2016.
 */
public class SoundManager {
    private static boolean soundEnable =true;
    public static String INTRO_MUSIC = "intro.wav";  // Example playSound(ViewManagement.SoundManager.INTRO_MUSIC) etc
    public static String FAIL = "fail.wav";
    public static String CHANGE = "change.wav";
    public static String SUCCESS = "success.wav";
    public static String CLICK = "tik.wav";
    public static String WEAPON = "weapon.wav";
    
    public static void enableSound(){
        soundEnable= true;
    }
    public static void disableSound(){
        soundEnable= false;
    }
    public static boolean isSoundEnable(){
        return soundEnable;
    }
    public static void playSound(String filename){
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
