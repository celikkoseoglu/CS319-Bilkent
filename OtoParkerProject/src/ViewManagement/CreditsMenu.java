package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class CreditsMenu extends Menu {

    private final int headerY = 40;
    private final int name1Y = 120;
    private final int name2Y = 240;
    private final int name3Y = 360;
    private final int name4Y = 480;

    private final String name1 = "Ali Çetin";
    private final String name2 = "Arda Usman";
    private final String name3 = "Çelik Köseoğlu";
    private final String name4 = "Hüseyin Beyan";

    private JButton backToMainMenuButton;

    public CreditsMenu(MenuManager manager) {
        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        backToMainMenuButton = new JButton("<- Main ViewManagement.Menu");
        backToMainMenuButton.addActionListener(buttonListener);
        backToMainMenuButton.setBounds(10, 10, 150, 30);
        add(backToMainMenuButton);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        AttributedString upgradesString = new AttributedString("Credits");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));
        g2d.drawString(upgradesString.getIterator(), (800 - g2d.getFontMetrics().stringWidth("Credits")) / 2, headerY);

        AttributedString name1String = new AttributedString(name1);
        name1String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name1String.getIterator(), (800 - g2d.getFontMetrics().stringWidth(name1)) / 2, name1Y);

        AttributedString name2String = new AttributedString(name2);
        name2String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name2String.getIterator(), (800 - g2d.getFontMetrics().stringWidth(name2)) / 2, name2Y);

        AttributedString name3String = new AttributedString(name3);
        name3String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name3String.getIterator(), (800 - g2d.getFontMetrics().stringWidth(name3)) / 2, name3Y);

        AttributedString name4String = new AttributedString(name4);
        name4String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name4String.getIterator(), (800 - g2d.getFontMetrics().stringWidth(name4)) / 2, name4Y);
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }
        }
    }
}
