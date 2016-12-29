package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PauseMenu extends OtoParkerMenu {

    private JButton retryButton;
    private JButton upgradesButton;
    private JButton mainMenuButton;
    private JButton exitGameButton;

    private int currentLevel;

    public PauseMenu(MenuManager manager, int currentLevel) {
        super(manager);

        this.currentLevel = currentLevel;

        ButtonListener buttonListener = new ButtonListener();

        setPreferredSize(new Dimension(250, 400));

        setOpaque(false);

        retryButton = new OtoParkerJButton("Retry Level");
        upgradesButton = new OtoParkerJButton("Upgrades");
        mainMenuButton = new OtoParkerJButton("Main Menu");
        exitGameButton = new OtoParkerJButton("Exit Game");

        retryButton.setForeground(Color.white);
        upgradesButton.setForeground(Color.white);
        mainMenuButton.setForeground(Color.white);
        exitGameButton.setForeground(Color.white);

        retryButton.setBounds(350, 150, 150, 40);
        upgradesButton.setBounds(350, 220, 150, 40);
        mainMenuButton.setBounds(350, 290, 150, 40);
        exitGameButton.setBounds(350, 360, 150, 40);

        retryButton.addActionListener(buttonListener);
        upgradesButton.addActionListener(buttonListener);
        mainMenuButton.addActionListener(buttonListener);
        exitGameButton.addActionListener(buttonListener);

        add(retryButton);
        add(upgradesButton);
        add(mainMenuButton);
        add(exitGameButton);
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
            else if (e.getSource() == retryButton)
                manager.showLevel(currentLevel);
        }
    }
}
