package ViewManagement;

import GameManagement.Player;
import GameManagement.Unlockables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Arrays;

public class UpgradeCarMenu extends OtoParkerMenu {

    private JButton colorLeftButton, colorRightButton;
    private JButton weaponLeftButton, weaponRightButton;
    private JButton turningRadiusLeftButton, turningRadiusRightButton;
    private JButton backToMainMenuButton;
    private JButton upgradeButton;

    private JLabel currentStarCount;
    private LocalDataManager localDataManager;
    private Player player;
    private Unlockables unlockables;

    private Image carImage, weaponImage;
    private int currentColor = 0;
    private int currentWeapon = 0;
    private int currentTurningRadius = 0;
    private String currentColorImageFileName;
    private String currentWeaponImageFileName;
    private double currentTurningRadiusDouble = 0.6;

    public UpgradeCarMenu(MenuManager manager, Player player, LocalDataManager localDataManager) {

        super(manager);

        this.localDataManager = localDataManager;
        this.player = player;
        this.unlockables = localDataManager.getUnlockables();

        ButtonListener buttonListener = new ButtonListener();

        currentStarCount = new JLabel("Number of Stars Available: " + player.getNumberOfStars(), null, JLabel.CENTER);

        colorLeftButton = new OtoParkerJButton("<");
        colorRightButton = new OtoParkerJButton(">");
        weaponLeftButton = new OtoParkerJButton("<");
        weaponRightButton = new OtoParkerJButton(">");
        turningRadiusLeftButton = new OtoParkerJButton("<");
        turningRadiusRightButton = new OtoParkerJButton(">");
        backToMainMenuButton = new OtoParkerJButton("<- Main Menu");
        upgradeButton = new OtoParkerJButton("Upgrade!");

        colorLeftButton.addActionListener(buttonListener);
        colorRightButton.addActionListener(buttonListener);
        weaponLeftButton.addActionListener(buttonListener);
        weaponRightButton.addActionListener(buttonListener);
        turningRadiusLeftButton.addActionListener(buttonListener);
        turningRadiusRightButton.addActionListener(buttonListener);
        backToMainMenuButton.addActionListener(buttonListener);
        upgradeButton.addActionListener(buttonListener);

        colorLeftButton.setBounds(650, 150, 60, 40);
        colorRightButton.setBounds(720, 150, 60, 40);
        weaponLeftButton.setBounds(650, 250, 60, 40);
        weaponRightButton.setBounds(720, 250, 60, 40);
        turningRadiusLeftButton.setBounds(650, 350, 60, 40);
        turningRadiusRightButton.setBounds(720, 350, 60, 40);
        upgradeButton.setBounds(620, 450, 150, 40);
        currentStarCount.setBounds(350, 450, 200, 40);
        backToMainMenuButton.setBounds(10, 10, 150, 40);

        add(colorLeftButton);
        add(colorRightButton);
        add(weaponLeftButton);
        add(weaponRightButton);
        add(turningRadiusLeftButton);
        add(turningRadiusRightButton);
        add(backToMainMenuButton);
        add(upgradeButton);
        add(currentStarCount);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AttributedString upgradesString = new AttributedString("Upgrades");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        g2d.drawString(upgradesString.getIterator(), 340, 40);

        g2d.drawImage(carImage, 68, 150, 200, 320, this);
        g2d.drawImage(weaponImage, 158, 480, 20, 20, this);
        g2d.drawString(Double.toString(currentTurningRadiusDouble), 155, 520);

        g2d.drawRect(40, 80, 250, 460);
        g2d.drawString("Color", 320, 150);
        g2d.drawString("Weapon", 320, 250);
        g2d.drawString("Turning Radius", 320, 350);
    }


    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == colorLeftButton) {

                currentColorImageFileName = unlockables.getUnlockableCarColors().get(currentColor);
                carImage = Toolkit.getDefaultToolkit().getImage("images/" + currentColorImageFileName);

                if (currentColor >= unlockables.getUnlockableCarColors().size() - 1)
                    currentColor = 0;
                else
                    currentColor++;
                repaint();

            } else if (e.getSource() == colorRightButton) {

                currentColorImageFileName = unlockables.getUnlockableCarColors().get(currentColor);
                carImage = Toolkit.getDefaultToolkit().getImage("images/" + currentColorImageFileName);

                if (currentColor <= 0)
                    currentColor = unlockables.getUnlockableCarColors().size() - 1;
                else
                    currentColor--;
                repaint();

            } else if (e.getSource() == weaponLeftButton) {

                currentWeaponImageFileName = unlockables.getUnlockableCarWeapons().get(currentWeapon);
                weaponImage = Toolkit.getDefaultToolkit().getImage("images/" + currentWeaponImageFileName);

                if (currentWeapon >= unlockables.getUnlockableCarWeapons().size() - 1)
                    currentWeapon = 0;
                else
                    currentWeapon++;
                repaint();

            } else if (e.getSource() == weaponRightButton) {

                currentWeaponImageFileName = unlockables.getUnlockableCarWeapons().get(currentWeapon);
                weaponImage = Toolkit.getDefaultToolkit().getImage("images/" + currentWeaponImageFileName);

                if (currentWeapon <= 0)
                    currentWeapon = unlockables.getUnlockableCarWeapons().size() - 1;
                else
                    currentWeapon--;
                repaint();

            } else if (e.getSource() == turningRadiusLeftButton) {

                currentTurningRadiusDouble = unlockables.getUnlockableTurningRadiuses().get(currentTurningRadius);

                if (currentTurningRadius >= unlockables.getUnlockableTurningRadiuses().get(currentTurningRadius))
                    currentTurningRadius = 0;
                else
                    currentTurningRadius++;
                repaint();

            } else if (e.getSource() == turningRadiusRightButton) {
                currentTurningRadiusDouble = unlockables.getUnlockableTurningRadiuses().get(currentTurningRadius);

                if (currentTurningRadius <= 0)
                    currentTurningRadius = unlockables.getUnlockableTurningRadiuses().size() - 1;
                else
                    currentTurningRadius--;
                repaint();

            } else if (e.getSource() == upgradeButton) {

                int colorCost = 0;
                int weaponCost = 0;
                int turningRadiusCost = 0;

                if (!player.getUnlockedCarColors().contains(currentColorImageFileName)) {
                    colorCost = 1;
                }

                if (!player.getUnlockedCarWeapons().contains(currentWeaponImageFileName)) {
                    weaponCost = 5;
                }

                if (!player.getUnlockedCarTurningRadiuses().contains(currentTurningRadiusDouble)) {
                    turningRadiusCost = 5;
                }

                if ((colorCost + weaponCost + turningRadiusCost) <= player.getNumberOfStars()) {

                    int dialogResult = JOptionPane.showConfirmDialog(null, "Would upgrade? Total Cost: " + (colorCost + weaponCost + turningRadiusCost), "Warning", JOptionPane.YES_NO_OPTION);
                    if (dialogResult == JOptionPane.YES_OPTION) {
                        player.addNumberOfStars(-(colorCost + weaponCost + turningRadiusCost));

                        if (!player.getUnlockedCarColors().contains(currentColorImageFileName)) {
                            player.addUnlockedCar(currentColorImageFileName);
                        }

                        if (!player.getUnlockedCarWeapons().contains(currentWeaponImageFileName)) {
                            player.addUnlockedWeapon(currentWeaponImageFileName);
                        }

                        if (!player.getUnlockedCarTurningRadiuses().contains(currentTurningRadiusDouble)) {
                            player.addUnlockedTurningRadius(currentTurningRadiusDouble);
                        }

                        player.setCurrentCarColor(currentColorImageFileName);
                        player.setCurrentCarWeapon(currentWeaponImageFileName);
                        player.setCurrentCarTurningRadius(currentTurningRadiusDouble);

                        localDataManager.savePlayerStats(player);

                        Object[] ucc = player.getUnlockedCarColors().toArray();
                        Object[] ucw = player.getUnlockedCarWeapons().toArray();


                        String[] uctrStrArr = new String[player.getUnlockedCarTurningRadiuses().size()];
                        for (int i = 0; i < player.getUnlockedCarTurningRadiuses().size(); i++)
                            uctrStrArr[i] = Double.toString(player.getUnlockedCarTurningRadiuses().get(i));


                        //Second Step: convert Object array to String array
                        String[] uccStrArr = Arrays.copyOf(ucc, ucc.length, String[].class);
                        String[] ucwStrArr = Arrays.copyOf(ucw, ucw.length, String[].class);

                        Unlockables playersNewUnlocks = new Unlockables(uccStrArr, ucwStrArr, uctrStrArr);
                        localDataManager.saveUnlockables(playersNewUnlocks);
                    }
                } else
                    JOptionPane.showMessageDialog(null, "Go earn some more stars kiddo.");
            } else if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }

        }
    }
}