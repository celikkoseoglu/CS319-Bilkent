import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LevelsMenu extends Menu {

    private JButton backToMainMenuButton;

    public LevelsMenu(MenuManager manager) {
        super(manager);

        ButtonListener buttonListener = new ButtonListener();

        backToMainMenuButton = new JButton("<- Main Menu");
        backToMainMenuButton.addActionListener(buttonListener);
        backToMainMenuButton.setBounds(10, 10, 150, 30);
        add(backToMainMenuButton);

        for (int i = 1; i < 6; i++) {
            JButton button = new OtoParkerJButton(Integer.toString(i));
            button.addActionListener(buttonListener);
            button.setBounds(100 + (i - 1) * 128,160, 88, 88);
            add(button);
        }

        for (int i = 1; i < 6; i++) {
            JButton button = new OtoParkerJButton(Integer.toString(i + 5));
            button.addActionListener(buttonListener);
            button.setBounds(100 + (i - 1) * 128,  360, 88, 88);
            add(button);
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }
            else {
                System.out.println("Open Level: " + ((JButton)e.getSource()).getText());
            }
        }
    }
}