package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class MainMenu extends OtoParkerMenu {

    private JButton playButton;
    private JButton upgradeCarButton;
    private JButton instructionsButton;
    private JButton creditsButton;
    private JButton soundButton;

    public MainMenu(MenuManager manager) {

        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        playButton = new OtoParkerJButton("Play");
        upgradeCarButton = new OtoParkerJButton("Upgrade Car");
        instructionsButton = new OtoParkerJButton("Instructions");
        creditsButton = new OtoParkerJButton("Credits");
        soundButton = new OtoParkerJButton("Sound");

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

        playButton.setBounds(70, 150, 150, 40);
        upgradeCarButton.setBounds(70, 220, 150, 40);
        instructionsButton.setBounds(70, 290, 150, 40);
        creditsButton.setBounds(70, 360, 150, 40);
        soundButton.setBounds(70, 430, 150, 40);

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

        AttributedString upgradesString = new AttributedString("OtoParker");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 40));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        g2d.drawString(upgradesString.getIterator(), 50, 70);

        g2d.drawRect(350, 35, 400, 420);

        Image img1 = Toolkit.getDefaultToolkit().getImage("images/star.png");

        g2d.drawImage(img1, 405, 470, 10, 10, this);

        g2d.drawString("5   's available", 395, 480);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == playButton) {
                manager.showLevelScreen();
            }
            else if (e.getSource() == upgradeCarButton) {
                manager.showUpgradeCar();
            }
            else if (e.getSource() == instructionsButton) {
                manager.showInstructions();
            }
            else if (e.getSource() == creditsButton) {
                manager.showCredits();
            }
            else if (e.getSource() == soundButton) {
                if(SoundManager.isSoundEnable()){
                    SoundManager.disableSound();
                } else {
                    SoundManager.enableSound();
                }
            }

        }
    }
}
