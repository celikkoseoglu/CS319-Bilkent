package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by celikkoseoglu on 25/12/2016.
 */
public class PauseMenu extends Menu {

    private JButton upgradesButton;
    private JButton mainMenuButton;
    private JButton exitGameButton;
    private JButton resumeButton;

    public PauseMenu(MenuManager manager) {
        super(manager);

        setPreferredSize(new Dimension(250, 400));

        upgradesButton = new OtoParkerJButton("Upgrades");
        mainMenuButton = new OtoParkerJButton("Main Menu");
        exitGameButton = new OtoParkerJButton("Exit Game");
        resumeButton = new OtoParkerJButton("Resume");

        add(upgradesButton);
        add(mainMenuButton);
        add(exitGameButton);
        add(resumeButton);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2d.fillRect(10, 10, 230, 380);
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
