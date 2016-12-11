import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created by celikkoseoglu on 11/12/2016.
 */

public class MainMenu extends Menu {

    ButtonListener buttonListener;

    JButton playButton;
    JButton upgradeCarButton;
    JButton instructionsButton;
    JButton creditsButton;
    JButton soundButton;

    public MainMenu() {

        buttonListener = new ButtonListener();

        playButton = new JButton("Play");
        upgradeCarButton = new JButton("Upgrade Car");
        instructionsButton = new JButton("Instructions");
        creditsButton = new JButton("Credits");
        soundButton = new JButton("Sound");

        //mnemonics are not working.
        playButton.setMnemonic(KeyEvent.VK_P);
        upgradeCarButton.setMnemonic(KeyEvent.VK_U);
        instructionsButton.setMnemonic(KeyEvent.VK_I);
        creditsButton.setMnemonic(KeyEvent.VK_C);
        soundButton.setMnemonic(KeyEvent.VK_S);

        playButton.addActionListener(buttonListener);
        upgradeCarButton.addActionListener(buttonListener);
        instructionsButton.addActionListener(buttonListener);
        creditsButton.addActionListener(buttonListener);
        soundButton.addActionListener(buttonListener);

        playButton.setBounds(50, 150, 150, 23);
        upgradeCarButton.setBounds(50, 200, 150, 23);
        instructionsButton.setBounds(50, 250, 150, 23);
        creditsButton.setBounds(50, 300, 150, 23);
        soundButton.setBounds(50, 350, 150, 23);

        add(playButton);
        add(upgradeCarButton);
        add(instructionsButton);
        add(creditsButton);
        add(soundButton);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawString("OtoParker", 40, 40);
        g2d.drawRect(350, 50, 350, 500);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {


            if (e.getSource() == playButton)
                ((JButton)e.getSource()).setBackground(Color.red);
            else if (e.getSource() == upgradeCarButton)
                System.out.println("Show upgrade car screen!!");

        }
    }
}
