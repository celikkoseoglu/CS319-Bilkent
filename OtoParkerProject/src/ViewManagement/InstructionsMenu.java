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

        ImageIcon icon = new ImageIcon("images/instructions.jpg");
        Image image = icon.getImage();
        g.drawImage(image, 0, 50, (int)800, (int)500, null);

    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            SoundManager.playSound(SoundManager.CLICK);
            if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }
        }
    }
}
