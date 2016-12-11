import javax.swing.*;
import java.awt.*;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

/**
 * Created by celikkoseoglu on 11/12/2016.
 */
public class UpgradeCarMenu extends Menu {
    JButton colorLeftButton, colorRightButton;
    JButton weaponLeftButton, weaponRightButton;
    JButton turningRadiusLeftButton, turningRadiusRightButton;
    JButton upgradeCarButton;

    public UpgradeCarMenu() {

        colorLeftButton = new JButton("<");
        colorRightButton = new JButton(">");
        weaponLeftButton = new JButton("<");
        weaponRightButton = new JButton(">");
        turningRadiusLeftButton = new JButton("<");
        turningRadiusRightButton = new JButton(">");
        upgradeCarButton = new JButton("Upgrade!");

        colorLeftButton.setBounds(50, 150, 150, 23);
        colorRightButton.setBounds(50, 150, 150, 23);
        weaponLeftButton.setBounds(50, 150, 150, 23);
        weaponRightButton.setBounds(50, 150, 150, 23);
        turningRadiusLeftButton.setBounds(50, 150, 150, 23);
        turningRadiusRightButton.setBounds(50, 150, 150, 23);
        upgradeCarButton.setBounds(50, 150, 150, 23);

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

        AttributedString text = new AttributedString("Bunny rabits and flying ponies");
        text.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 24), 0, "Bunny rabits".length());
        text.addAttribute(TextAttribute.FOREGROUND, Color.RED, 0, "Bunny rabits".length());

        text.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD & Font.ITALIC, 32), 17, 17 + "flying ponies".length());
        text.addAttribute(TextAttribute.FOREGROUND, Color.BLUE, 17, 17 + "flying ponies".length());

        FontMetrics fm = g2d.getFontMetrics();
        LineMetrics lm = fm.getLineMetrics(text.getIterator(), 0, text.getIterator().getEndIndex(), g);

        g2d.drawString(text.getIterator(), 0, (int)lm.getAscent() + lm.getHeight());
    }
}
