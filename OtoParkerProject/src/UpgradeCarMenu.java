import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;

/**
 * Created by celikkoseoglu on 11/12/2016.
 */
public class UpgradeCarMenu extends Menu {

    JButton colorLeftButton, colorRightButton;
    JButton weaponLeftButton, weaponRightButton;
    JButton turningRadiusLeftButton, turningRadiusRightButton;
    JButton upgradeCarButton;

    private final int colorLineY = 350;
    private final int weaponLineY = 400;
    private final int turningRadiusLineY = 450;

    public UpgradeCarMenu(MenuManager manager) {

        super(manager);

        colorLeftButton = new JButton("<");
        colorRightButton = new JButton(">");
        weaponLeftButton = new JButton("<");
        weaponRightButton = new JButton(">");
        turningRadiusLeftButton = new JButton("<");
        turningRadiusRightButton = new JButton(">");
        upgradeCarButton = new JButton("Upgrade!");

        colorLeftButton.setBounds(700, colorLineY, 30, 30);
        colorRightButton.setBounds(740, colorLineY, 30, 30);
        weaponLeftButton.setBounds(700, weaponLineY, 30, 30);
        weaponRightButton.setBounds(740, weaponLineY, 30, 30);
        turningRadiusLeftButton.setBounds(700, turningRadiusLineY, 30, 30);
        turningRadiusRightButton.setBounds(740, turningRadiusLineY, 30, 30);
        upgradeCarButton.setBounds(650, 520, 30, 30);

        add(colorLeftButton);
        add(colorRightButton);
        add(weaponLeftButton);
        add(weaponRightButton);
        add(turningRadiusLeftButton);
        add(turningRadiusRightButton);
        add(upgradeCarButton);
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
}
