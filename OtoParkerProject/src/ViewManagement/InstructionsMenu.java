package ViewManagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.text.AttributedString;

public class InstructionsMenu extends OtoParkerMenu {

    private JButton backToMainMenuButton;

    public InstructionsMenu(MenuManager manager) {
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

        AttributedString controlsString = new AttributedString("Controls");
        controlsString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        controlsString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        AttributedString upgradesString = new AttributedString("Mechanics");
        upgradesString.addAttribute(TextAttribute.FONT, new Font("Arial", Font.BOLD, 30));
        upgradesString.addAttribute(TextAttribute.FOREGROUND, new Color(201, 103, 32));

        g2d.drawString(controlsString.getIterator(), 250, 40);
        g2d.drawString(upgradesString.getIterator(), 500, 40);
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
