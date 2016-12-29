package GameManagement;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * Created by ASUS on 11.12.2016.
 */
public class SoundManager {
    public static String INTRO_MUSIC = "intro.wav";  // Example playSound(GameManagement.SoundManager.INTRO_MUSIC) etc
    public static String FAIL = "fail.wav";
    public static String CHANGE = "change.wav";
    public static String SUCCESS = "success.wav";
    public static String CLICK = "tik.wav";
    public static String WEAPON = "weapon.wav";
    private static boolean soundEnable = true;

    public static void enableSound() {
        soundEnable = true;
    }

    public static void disableSound() {
        soundEnable = false;
    }

    public static boolean isSoundEnable() {
        return soundEnable;
    }

    public static void playSound(String filename) {
        if (isSoundEnable()) {
            try {
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(new File(filename)));
                clip.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
