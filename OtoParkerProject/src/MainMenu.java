import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class MainMenu extends Menu {

    private JButton playButton;
    private JButton upgradeCarButton;
    private JButton instructionsButton;
    private JButton creditsButton;
    private JButton soundButton;

    public MainMenu(MenuManager manager) {

        super(manager);

        ButtonListener buttonListener = new ButtonListener();

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

        AttributedString upgradesString = new AttributedString("OtoParker");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        FontMetrics fm = g2d.getFontMetrics();
        LineMetrics lm = fm.getLineMetrics(upgradesString.getIterator(), 0, upgradesString.getIterator().getEndIndex(), g);

        g2d.drawString(upgradesString.getIterator(), 40, 40);

        g2d.drawRect(350, 50, 350, 500);
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
                //TODO enable disable sound
            }

        }
    }
}
