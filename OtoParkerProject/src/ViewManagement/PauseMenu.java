package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends OtoParkerMenu {

    private JButton upgradesButton;
    private JButton mainMenuButton;
    private JButton exitGameButton;
    private JButton resumeButton;

    private int currentLevel;

    public PauseMenu(MenuManager manager, int currentLevel) {
        super(manager);

        this.currentLevel = currentLevel;

        ButtonListener buttonListener = new ButtonListener();

        setPreferredSize(new Dimension(250, 400));

        setOpaque(false);

        upgradesButton = new OtoParkerJButton("Upgrades");
        mainMenuButton = new OtoParkerJButton("Main Menu");
        exitGameButton = new OtoParkerJButton("Exit Game");
        resumeButton = new OtoParkerJButton("Resume");

        upgradesButton.setForeground(Color.white);
        mainMenuButton.setForeground(Color.white);
        exitGameButton.setForeground(Color.white);
        resumeButton.setForeground(Color.white);

        upgradesButton.setBounds(350, 150, 150, 40);
        mainMenuButton.setBounds(350, 220, 150, 40);
        exitGameButton.setBounds(350, 290, 150, 40);
        resumeButton.setBounds(350, 360, 150, 40);

        upgradesButton.addActionListener(buttonListener);
        mainMenuButton.addActionListener(buttonListener);
        exitGameButton.addActionListener(buttonListener);
        resumeButton.addActionListener(buttonListener);

        add(upgradesButton);
        add(mainMenuButton);
        add(exitGameButton);
        add(resumeButton);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == upgradesButton)
                manager.showUpgradeCar();
            else if (e.getSource() == mainMenuButton)
                manager.showMainMenu();
            else if (e.getSource() == exitGameButton)
                System.exit(1);
        }
    }
}
