package ViewManagement;

import javax.imageio.ImageIO;
import javax.swing.*;

import GameManagement.Player;
import GameObjects.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.io.File;
import java.text.AttributedString;
import java.awt.image.*;

public class UpgradeCarMenu extends OtoParkerMenu {

    private JButton colorLeftButton, colorRightButton;
    private JButton weaponLeftButton, weaponRightButton;
    private JButton DturningRadius, IncTurningRadius;
    private JButton backToMainMenuButton;

    private final int colorLineY = 150;
    private final int weaponLineY = 250;
    private final int turningRadiusLineY = 350;

    JPanel bluep = new JPanel();
    ImageIcon blue_porsche = new ImageIcon("images/porsche.png");
    JLabel bl = new JLabel("",blue_porsche,JLabel.CENTER);

    JPanel redp = new JPanel();
    ImageIcon red_porsche = new ImageIcon("images/porsche_turuncu.png");
    JLabel rl = new JLabel("",red_porsche,JLabel.CENTER);

    JPanel wp = new JPanel();
    ImageIcon lx = new ImageIcon("images/car.png");
    JLabel wl = new JLabel("",lx,JLabel.CENTER);

    JPanel blackp = new JPanel();
    ImageIcon bb = new ImageIcon("images/black.png");
    JLabel black = new JLabel("",bb,JLabel.CENTER);

    JPanel laserPanel = new JPanel();
    ImageIcon lz = new ImageIcon("images/daringfireball.png");
    JLabel ll = new JLabel("",lz,JLabel.CENTER);

    JPanel bomba = new JPanel();
    ImageIcon bmb = new ImageIcon("images/1.png");
    JLabel bombaDotCom = new JLabel("",bmb,JLabel.CENTER);

    Player player;

    public UpgradeCarMenu(MenuManager manager, Player player) {

        super(manager);

        this.player = player;

        ButtonListener buttonListener = new ButtonListener();


        colorLeftButton = new JButton("<");
        colorRightButton = new JButton(">");
        weaponLeftButton = new JButton("<");
        weaponRightButton = new JButton(">");
        DturningRadius = new JButton("<");
        IncTurningRadius = new JButton(">");
        backToMainMenuButton = new JButton("<- Main ViewManagement.Menu");

        colorLeftButton.addActionListener(buttonListener);
        colorRightButton.addActionListener(buttonListener);
        weaponLeftButton.addActionListener(buttonListener);
        weaponRightButton.addActionListener(buttonListener);
        DturningRadius.addActionListener(buttonListener);
        IncTurningRadius.addActionListener(buttonListener);
        backToMainMenuButton.addActionListener(buttonListener);

        colorLeftButton.setBounds(700, colorLineY, 30, 30);
        colorRightButton.setBounds(740, colorLineY, 30, 30);
        weaponLeftButton.setBounds(700, weaponLineY, 30, 30);
        weaponRightButton.setBounds(740, weaponLineY, 30, 30);
        DturningRadius.setBounds(700, turningRadiusLineY, 30, 30);
        IncTurningRadius.setBounds(740, turningRadiusLineY, 30, 30);
        backToMainMenuButton.setBounds(10, 10, 150, 30);


        bluep.setPreferredSize(new Dimension(50,70));
        bluep.setBounds(125,220,100,100);
        bluep.add(bl);
        bluep.setVisible(false);


        redp.setPreferredSize(new Dimension(50,70));
        redp.setBounds(125,220,100,160);
        redp.add(rl);
        redp.setVisible(false);

        wp.setPreferredSize(new Dimension(50,70));
        wp.setBounds(125,220,100,160);
        wp.add(wl);
        wp.setVisible(false);

        blackp.setPreferredSize(new Dimension(50,70));
        blackp.setBounds(125,220,100,160);
        blackp.add(black);
        blackp.setVisible(false);

        laserPanel.setPreferredSize(new Dimension(50,50));
        laserPanel.setBounds(125,400,50,50);
        laserPanel.add(ll);
        laserPanel.setVisible(false);

        bomba.setPreferredSize(new Dimension(50,50));
        bomba.setBounds(125,400,50,50);
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
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        /* ATTRIBUTED STRING EXAMPLE

        AttributedString text = new AttributedString("Bunny rabits and flying ponies");
        text.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 24), 0, "Bunny rabits".length());
        text.addAttribute(TextAttribute.FOREGROUND, Color.RED, 0, "Bunny rabits".length());

        text.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD & Font.ITALIC, 32), 17, 17 + "flying ponies".length());
        text.addAttribute(TextAttribute.FOREGROUND, Color.BLUE, 17, 17 + "flying ponies".length());

        FontMetrics fm = g2d.getFontMetrics();
        LineMetrics lm = fm.getLineMetrics(text.getIterator(), 0, text.getIterator().getEndIndex(), g);

        g2d.drawString(text.getIterator(), 0, (int)lm.getAscent() + lm.getHeight());*/

        AttributedString upgradesString = new AttributedString("Upgrades");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        FontMetrics fm = g2d.getFontMetrics();
        LineMetrics lm = fm.getLineMetrics(upgradesString.getIterator(), 0, upgradesString.getIterator().getEndIndex(), g);

        g2d.drawString(upgradesString.getIterator(), 340, 40);


        //TODO change this with the current car's image
        g2d.drawRect(40, 40, 250, 500);


        g2d.drawString("Color", 320, colorLineY);
        g2d.drawString("Weapon", 320, weaponLineY);
        g2d.drawString("Turning Radius", 320, turningRadiusLineY);
    }


    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == colorLeftButton) {
                if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/porsche.png" : "images/porsche.png")){
                    blackp.setVisible(false);
                    bluep.setVisible(false);
                    wp.setVisible(true);
                    redp.setVisible(false);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/car.png" : "images/car.png";
               }
               else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/porsche_turuncu.png" : "images/porsche_turuncu.png")){
                    blackp.setVisible(false);
                    redp.setVisible(false);
                    wp.setVisible(false);
                    bluep.setVisible(true);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/porsche.png" : "images/porsche.png";
               }
               else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/car.png" : "images/car.png")){
                    blackp.setVisible(true);
                    redp.setVisible(false);
                    wp.setVisible(false);
                    bluep.setVisible(false);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/black.png" : "images/black.png";
               }
               else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/black.png" : "images/black.png")){
                    blackp.setVisible(false);
                    redp.setVisible(true);
                    wp.setVisible(false);
                    bluep.setVisible(false);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/porsche_turuncu.png" : "images/porsche_turuncu.png";
               }
            }

            else if (e.getSource() == colorRightButton) {
                if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/porsche.png" : "images/porsche.png")){
                    blackp.setVisible(false);
                    bluep.setVisible(false);
                    wp.setVisible(false);
                    redp.setVisible(true);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/porsche_turuncu.png" : "images/porsche_turuncu.png";
                }
                else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/porsche_turuncu.png" : "images/porsche_turuncu.png")){
                    blackp.setVisible(true);
                    redp.setVisible(false);
                    wp.setVisible(false);
                    bluep.setVisible(false);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/black.png" : "images/black.png";
                }
                else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/car.png" : "images/car.png")){
                    blackp.setVisible(false);
                    redp.setVisible(false);
                    wp.setVisible(false);
                    bluep.setVisible(true);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/porsche.png" : "images/porsche.png";
                }
                else if(Car.carImageDir.equals(System.getProperty("os.name").contains("Mac") ? "images/black.png" : "images/black.png")){
                    redp.setVisible(false);
                    wp.setVisible(true);
                    bluep.setVisible(false);
                    blackp.setVisible(false);
                    Car.carImageDir =  System.getProperty("os.name").contains("Mac") ? "images/car.png" : "images/car.png";
                }
            }

            else if (e.getSource() == weaponLeftButton) {
                if (Cannonball.im.equals(System.getProperty("os.name").contains("Mac") ? "images/1.png" : "images/1.png")){
                    laserPanel.setVisible(true);
                    bomba.setVisible(false);
                    Cannonball.im = System.getProperty("os.name").contains("Mac") ? "images/daringfireball.png" : "images/daringfireball.png";
                }
                else if (Cannonball.im.equals(System.getProperty("os.name").contains("Mac") ? "images/daringfireball.png" : "images/daringfireball.png")){
                    laserPanel.setVisible(false);
                    bomba.setVisible(true);
                    Cannonball.im = System.getProperty("os.name").contains("Mac") ? "images/1.png" : "images/1.png";
                }

            }
            else if (e.getSource() == weaponRightButton) {
                if (Cannonball.im.equals(System.getProperty("os.name").contains("Mac") ? "images/1.png" : "images/1.png")){
                    laserPanel.setVisible(true);
                    bomba.setVisible(false);
                    Cannonball.im = System.getProperty("os.name").contains("Mac") ? "images/daringfireball.png" : "images/daringfireball.png";
                }
                else if (Cannonball.im.equals(System.getProperty("os.name").contains("Mac") ? "images/daringfireball.png" : "images/daringfireball.png")){
                    laserPanel.setVisible(false);
                    bomba.setVisible(true);
                    Cannonball.im = System.getProperty("os.name").contains("Mac") ? "images/1.png" : "images/1.png";
                }            }
            else if (e.getSource() == DturningRadius) {
                    player.setCurrentCarTurningRadius(player.getCurrentCarTurningRadius()-0.5);
            }
            else if (e.getSource() == IncTurningRadius) {
                player.setCurrentCarTurningRadius(player.getCurrentCarTurningRadius()+1);

            }
            else if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }

        }
    }
}
