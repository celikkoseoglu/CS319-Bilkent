import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.LineMetrics;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class CreditsMenu extends Menu {

    private final int headerY = 40;
    private final int name1Y = 120;
    private final int name2Y = 240;
    private final int name3Y = 360;
    private final int name4Y = 480;

    private JButton backToMainMenuButton;

    public CreditsMenu(MenuManager manager) {
        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        backToMainMenuButton = new JButton("<- Main Menu");
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

        FontMetrics fm = g2d.getFontMetrics();
        LineMetrics lm = fm.getLineMetrics(upgradesString.getIterator(), 0, upgradesString.getIterator().getEndIndex(), g);

        g2d.drawString(upgradesString.getIterator(), 340, headerY);

        AttributedString name1String = new AttributedString("Ali Çetin");
        name1String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name1String.getIterator(), 340, name1Y);

        AttributedString name2String = new AttributedString("Arda Usman");
        name2String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name2String.getIterator(), 340, name2Y);

        AttributedString name3String = new AttributedString("Çelik Köseoğlu");
        name3String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name3String.getIterator(), 340, name3Y);

        AttributedString name4String = new AttributedString("Hüseyin Beyan");
        name4String.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 28));
        g2d.drawString(name4String.getIterator(), 340, name4Y);
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
