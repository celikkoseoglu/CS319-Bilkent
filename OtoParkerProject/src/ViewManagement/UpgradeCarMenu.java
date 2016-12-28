package ViewManagement;

import GameManagement.Player;
import GameManagement.Unlockables;
import GameObjects.Cannonball;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class UpgradeCarMenu extends OtoParkerMenu {

    private JButton colorLeftButton, colorRightButton;
    private JButton weaponLeftButton, weaponRightButton;
    private JButton DturningRadius, IncTurningRadius;
    private JButton backToMainMenuButton;
    private JButton upgradeButton;

    private final int colorLineY = 150;
    private final int weaponLineY = 250;
    private final int turningRadiusLineY = 350;

    private String intendedColor = "";
    private String intendedWeapon = "";
    private boolean incTR = false;

    private boolean colorChanged;
    private boolean weaponChanged;


    private JPanel bluep = new JPanel();
    private ImageIcon blue_porsche = new ImageIcon("images/porsche.png");
    private JLabel bl = new JLabel("", blue_porsche, JLabel.CENTER);

    private JPanel redp = new JPanel();
    private ImageIcon red_porsche = new ImageIcon("images/porsche_turuncu.png");
    private JLabel rl = new JLabel("", red_porsche, JLabel.CENTER);

    private JPanel wp = new JPanel();
    private ImageIcon lx = new ImageIcon("images/car.png");
    private JLabel wl = new JLabel("", lx, JLabel.CENTER);
    private JPanel blackp = new JPanel();
    private ImageIcon bb = new ImageIcon("images/black.png");
    private JLabel black = new JLabel("", bb, JLabel.CENTER);
    private JPanel laserPanel = new JPanel();
    private ImageIcon lz = new ImageIcon("images/daringfireball.png");
    private JLabel ll = new JLabel("", lz, JLabel.CENTER);
    private JPanel bomba = new JPanel();
    private ImageIcon bmb = new ImageIcon("images/1.png");
    private JLabel bombaDotCom = new JLabel("", bmb, JLabel.CENTER);
    private JLabel scount;
    private JLabel costLabel;
    private LocalDataManager localDataManager;
    private Player player;
    private Unlockables unlockables;

    Image img1, img2;
    int currentColor = 0;
    int currentWeapon = 0;
    int totalCost = 0;

    public UpgradeCarMenu(MenuManager manager, Player player, LocalDataManager localDataManager) {

        super(manager);

        this.localDataManager = localDataManager;
        this.player = player;
        this.unlockables = localDataManager.getUnlockables();

        ButtonListener buttonListener = new ButtonListener();

        scount = new JLabel("Number of Stars Available: " + player.getNumberOfStars(), null, JLabel.CENTER);
        costLabel = new JLabel("Total Cost: ");

        colorLeftButton = new OtoParkerJButton("<");
        colorRightButton = new OtoParkerJButton(">");
        weaponLeftButton = new OtoParkerJButton("<");
        weaponRightButton = new OtoParkerJButton(">");
        DturningRadius = new OtoParkerJButton("<");
        IncTurningRadius = new OtoParkerJButton(">");
        backToMainMenuButton = new OtoParkerJButton("<- Main Menu");
        upgradeButton = new OtoParkerJButton("Upgrade!");

        colorLeftButton.addActionListener(buttonListener);
        colorRightButton.addActionListener(buttonListener);
        weaponLeftButton.addActionListener(buttonListener);
        weaponRightButton.addActionListener(buttonListener);
        DturningRadius.addActionListener(buttonListener);
        IncTurningRadius.addActionListener(buttonListener);
        backToMainMenuButton.addActionListener(buttonListener);
        upgradeButton.addActionListener(buttonListener);

        colorLeftButton.setBounds(650, colorLineY, 60, 40);
        colorRightButton.setBounds(720, colorLineY, 60, 40);
        weaponLeftButton.setBounds(650, weaponLineY, 60, 40);
        weaponRightButton.setBounds(720, weaponLineY, 60, 40);
        DturningRadius.setBounds(650, turningRadiusLineY, 60, 40);
        IncTurningRadius.setBounds(720, turningRadiusLineY, 60, 40);
        upgradeButton.setBounds(620, 450, 150, 40);
        scount.setBounds(350, 450, 200, 40);
        costLabel.setBounds(350, 500, 200, 40);
        backToMainMenuButton.setBounds(10, 10, 150, 40);

        bl.setOpaque(false);
        bluep.setPreferredSize(new Dimension(50, 70));
        bluep.setBounds(125, 220, 100, 100);
        bluep.add(bl);
        bluep.setVisible(false);

        rl.setOpaque(false);
        redp.setPreferredSize(new Dimension(50, 70));
        redp.setBounds(125, 220, 100, 160);
        redp.add(rl);
        redp.setVisible(false);

        wl.setOpaque(false);
        wp.setPreferredSize(new Dimension(50, 70));
        wp.setBounds(125, 220, 100, 160);
        wp.add(wl);
        wp.setVisible(false);

        black.setOpaque(false);
        blackp.setPreferredSize(new Dimension(50, 70));
        blackp.setBounds(125, 220, 100, 160);
        blackp.add(black);
        blackp.setVisible(false);

        ll.setOpaque(false);
        laserPanel.setPreferredSize(new Dimension(50, 50));
        laserPanel.setBounds(125, 400, 50, 50);
        laserPanel.add(ll);
        laserPanel.setOpaque(false);
        laserPanel.setVisible(false);

        bombaDotCom.setOpaque(false);
        bomba.setPreferredSize(new Dimension(50, 50));
        bomba.setBounds(125, 400, 50, 50);
        bomba.setOpaque(false);
        bomba.add(bombaDotCom);
        bomba.setVisible(false);

        add(bluep);
        add(redp);
        add(wp);
        add(blackp);
        add(laserPanel);
        add(bomba);

        add(colorLeftButton);
        add(colorRightButton);
        add(weaponLeftButton);
        add(weaponRightButton);
        add(DturningRadius);
        add(IncTurningRadius);
        add(backToMainMenuButton);
        add(upgradeButton);
        add(scount);
        add(costLabel);
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


        g2d.drawImage(img1, 68, 150, 200, 320, this);
        g2d.drawImage(img2, 158, 480, 20, 20, this);

        //TODO change this with the current car's image
        g2d.drawRect(40, 80, 250, 460);

        g2d.drawString("Color", 320, colorLineY);
        g2d.drawString("Weapon", 320, weaponLineY);
        g2d.drawString("Turning Radius", 320, turningRadiusLineY);
    }


    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == colorLeftButton) {

                String currentCar = unlockables.getUnlockableCarColors().get(currentColor);
                img1 = Toolkit.getDefaultToolkit().getImage("images/" + currentCar);

                if (currentColor >= unlockables.getUnlockableCarColors().size() - 1)
                    currentColor = 0;
                else
                    currentColor++;
                repaint();

                if (!player.getUnlockedCarColors().contains(currentCar)) {
                    totalCost += 1;
                    costLabel.setText("Total Cost: " + totalCost);
                }

            } else if (e.getSource() == colorRightButton) {

                String currentCar = unlockables.getUnlockableCarColors().get(currentColor);
                img1 = Toolkit.getDefaultToolkit().getImage("images/" + currentCar);

                if (currentColor <= 0)
                    currentColor = unlockables.getUnlockableCarColors().size() - 1;
                else
                    currentColor--;
                repaint();

            } else if (e.getSource() == weaponLeftButton) {

                String currentCar = unlockables.getUnlockableCarWeapons().get(currentWeapon);
                img2 = Toolkit.getDefaultToolkit().getImage("images/" + currentCar);

                if (currentWeapon >= unlockables.getUnlockableCarWeapons().size() - 1)
                    currentWeapon = 0;
                else
                    currentWeapon++;
                repaint();

            } else if (e.getSource() == weaponRightButton) {

                String currentCar = unlockables.getUnlockableCarWeapons().get(currentWeapon);
                img2 = Toolkit.getDefaultToolkit().getImage("images/" + currentCar);

                if (currentWeapon <= 0)
                    currentWeapon = unlockables.getUnlockableCarWeapons().size() - 1;
                else
                    currentWeapon--;
                repaint();

            } else if (e.getSource() == DturningRadius) {
                player.setCurrentCarTurningRadius(player.getCurrentCarTurningRadius() - 0.5);
            } else if (e.getSource() == IncTurningRadius) {
                if (player.getUnlockedCarTurningRadiuses().contains(player.getCurrentCarTurningRadius() + 1) || incTR) {
                    player.setCurrentCarTurningRadius(player.getCurrentCarTurningRadius() + 1);
                } else
                    incTR = true;


            } else if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            } else if (e.getSource() == upgradeButton) {
                colorChanged = player.changeCarColor(intendedColor);
                weaponChanged = player.changeWeapon(intendedWeapon);
                if (incTR)
                    player.increaseTR();
                scount.setText("Number of Stars Available: "  + player.getNumberOfStars());

            }

        }
    }
}
