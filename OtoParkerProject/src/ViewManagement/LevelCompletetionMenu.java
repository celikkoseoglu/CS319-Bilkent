package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by celikkoseoglu on 28/12/2016.
 */
public class LevelCompletetionMenu extends OtoParkerMenu {

    private JLabel statusLabel;

    private JButton retryLevelButton;
    private JButton upgradesButton;
    private JButton mainMenuButton;
    private JButton nextLevelButton;

    private int level;

    public LevelCompletetionMenu(MenuManager manager) {
        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        setPreferredSize(new Dimension(250, 400));

        setOpaque(false);

        statusLabel = new JLabel("Level Completed?");
        retryLevelButton = new OtoParkerJButton("Retry Level");
        upgradesButton = new OtoParkerJButton("Upgrades");
        mainMenuButton = new OtoParkerJButton("Main Menu");
        nextLevelButton = new OtoParkerJButton("Next Level");

        statusLabel.setForeground(Color.white);
        retryLevelButton.setForeground(Color.white);
        upgradesButton.setForeground(Color.white);
        mainMenuButton.setForeground(Color.white);
        nextLevelButton.setForeground(Color.white);

        statusLabel.setBounds(350, 80, 150, 40);
        retryLevelButton.setBounds(350, 150, 150, 40);
        upgradesButton.setBounds(350, 220, 150, 40);
        mainMenuButton.setBounds(350, 290, 150, 40);
        nextLevelButton.setBounds(350, 360, 150, 40);

        retryLevelButton.addActionListener(buttonListener);
        upgradesButton.addActionListener(buttonListener);
        mainMenuButton.addActionListener(buttonListener);
        nextLevelButton.addActionListener(buttonListener);

        add(statusLabel);
        add(retryLevelButton);
        add(upgradesButton);
        add(mainMenuButton);
        add(nextLevelButton);
    }

    public void setLevel(int level) {
        this.level = level;
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == retryLevelButton)
                manager.showLevel(level); // buna basınca hala 1. level'a gidiyo amaaa...
            else if (e.getSource() == upgradesButton)
                manager.showUpgradeCar();
            else if (e.getSource() == mainMenuButton)
                manager.showMainMenu();
            else if (e.getSource() == nextLevelButton)
                if (level == 10)
                    manager.showCredits();
                else
                    manager.showLevel(level + 1);
        }
    }
}
