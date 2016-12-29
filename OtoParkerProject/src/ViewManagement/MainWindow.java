package ViewManagement;

import GameManagement.SoundManager;

import javax.swing.*;

public class MainWindow {

    public static void main(String args[]) throws Exception {
        JFrame myFrame = new JFrame("OtoParker");
        myFrame.setSize(800, 600);
        myFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuManager menuManager = new MenuManager(myFrame);
        myFrame.setVisible(true);
        myFrame.setResizable(false);
        SoundManager.playSound(SoundManager.INTRO_MUSIC);
    }
}