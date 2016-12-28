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
    private JButton exitGameButton;

    private JButton nextLevelButton;

    public LevelCompletetionMenu(MenuManager manager) {
        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        setPreferredSize(new Dimension(250, 400));

        setOpaque(false);


        upgradesButton = new OtoParkerJButton("Upgrades");
        mainMenuButton = new OtoParkerJButton("Main Menu");
        exitGameButton = new OtoParkerJButton("Exit Game");
        nextLevelButton = new OtoParkerJButton("Next Level");

        upgradesButton.setForeground(Color.white);
        mainMenuButton.setForeground(Color.white);
        exitGameButton.setForeground(Color.white);
        nextLevelButton.setForeground(Color.white);

        upgradesButton.setBounds(350, 150, 150, 40);
        mainMenuButton.setBounds(350, 220, 150, 40);
        exitGameButton.setBounds(350, 290, 150, 40);
        nextLevelButton.setBounds(350, 360, 150, 40);

        upgradesButton.addActionListener(buttonListener);
        mainMenuButton.addActionListener(buttonListener);
        exitGameButton.addActionListener(buttonListener);
        nextLevelButton.addActionListener(buttonListener);

        add(upgradesButton);
        add(mainMenuButton);
        add(exitGameButton);
        add(nextLevelButton);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == upgradesButton)
                manager.showUpgradeCar();
            else if (e.getSource() == mainMenuButton)
                manager.showMainMenu();
            else if (e.getSource() == exitGameButton)
                System.exit(1);
        }
    }
}
