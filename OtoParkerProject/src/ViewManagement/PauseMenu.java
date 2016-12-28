package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by celikkoseoglu on 25/12/2016.
 */
public class PauseMenu extends OtoParkerMenu {

    private JButton upgradesButton;
    private JButton mainMenuButton;
    private JButton exitGameButton;
    private JButton resumeButton;

    public PauseMenu(MenuManager manager) {
        super(manager);

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

        add(upgradesButton);
        add(mainMenuButton);
        add(exitGameButton);
        add(resumeButton);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
//            if (e.getSource() == backToMainMenuButton) {
//                manager.showMainMenu();
//            }
        }
    }
}
