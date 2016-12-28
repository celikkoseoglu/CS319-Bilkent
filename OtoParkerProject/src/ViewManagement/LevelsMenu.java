package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LevelsMenu extends OtoParkerMenu {

    private JButton backToMainMenuButton;

    private String[] progress;

    private ArrayList<Integer> levelTimes;

    public LevelsMenu(MenuManager manager, LocalDataManager mgr) {
        super(manager);

        this.levelTimes = new ArrayList<>();

        progress = mgr.readText("levelProgress.txt", false).split("\\|");

        ButtonListener buttonListener = new ButtonListener();

        backToMainMenuButton = new OtoParkerJButton("<- Main Menu");
        backToMainMenuButton.addActionListener(buttonListener);
        backToMainMenuButton.setBounds(10, 10, 150, 30);
        add(backToMainMenuButton);

        for (String s : progress) {
            String[] levels = s.split(":");
            levelTimes.add(Integer.parseInt(levels[1]));
        }

        boolean previousUnlocked = true;

        for (int i = 1; i < 6; i++) {
            if (i > 1)
                previousUnlocked = Integer.parseInt(progress[i - 2].split(":")[2]) > 0;

            JButton button = new OtoParkerJButton(Integer.toString(i));
            button.setEnabled(previousUnlocked);
            button.addActionListener(buttonListener);
            button.setBounds(100 + (i - 1) * 128,160, 88, 88);
            add(button);
        }

        for (int i = 1; i < 6; i++) {
            if (i > 1)
                previousUnlocked = Integer.parseInt(progress[i + 2].split(":")[2]) > 0;

            JButton button = new OtoParkerJButton(Integer.toString(i + 5));
            button.setEnabled(previousUnlocked);
            button.addActionListener(buttonListener);
            button.setBounds(100 + (i - 1) * 128,  360, 88, 88);
            add(button);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Image img1 = Toolkit.getDefaultToolkit().getImage("images/star.png");

        for (int i = 1; i < 6; i++) {
            int stars = Integer.parseInt(progress[i - 1].split(":")[2]);

            switch (stars) {
                case 3:
                    g2d.drawImage(img1,  110 + (i - 1) * 128,260, 10, 10, this);
                    g2d.drawImage(img1,  139 + (i - 1) * 128,260, 10, 10, this);
                    g2d.drawImage(img1,  168 + (i - 1) * 128,260, 10, 10, this);
                    break;
                case 2:
                    g2d.drawImage(img1,  130 + (i - 1) * 128,260, 10, 10, this);
                    g2d.drawImage(img1,  154 + (i - 1) * 128,260, 10, 10, this);
                    break;
                case 1:
                    g2d.drawImage(img1,  140 + (i - 1) * 128,260, 10, 10, this);
                    break;
            }
        }

        for (int i = 1; i < 6; i++) {
            int stars = Integer.parseInt(progress[i + 3].split(":")[2]);

            switch (stars) {
                case 3:
                    g2d.drawImage(img1,  110 + (i - 1) * 128,460, 10, 10, this);
                    g2d.drawImage(img1,  139 + (i - 1) * 128,460, 10, 10, this);
                    g2d.drawImage(img1,  168 + (i - 1) * 128,460, 10, 10, this);
                    break;
                case 2:
                    g2d.drawImage(img1,  130 + (i - 1) * 128,460, 10, 10, this);
                    g2d.drawImage(img1,  154 + (i - 1) * 128,460, 10, 10, this);
                    break;
                case 1:
                    g2d.drawImage(img1,  140 + (i - 1) * 128,460, 10, 10, this);
                    break;
            }
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            SoundManager.playSound(SoundManager.CLICK);
            if (e.getSource() == backToMainMenuButton)
                manager.showMainMenu();
            else {
                int levelNo = Integer.parseInt(((JButton) e.getSource()).getText());
                manager.showLevel(levelNo);
            }
        }
    }
}