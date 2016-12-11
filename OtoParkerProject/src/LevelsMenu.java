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

        for (int i = 1; i < 2; i++) {
            for (int j = 1; j < 6; j++) {
                JButton button = new JButton(Integer.toString(i));
                button.addActionListener(buttonListener);
                button.setBounds(i * 30, j * 100 + 50, 30, 30);
                add(button);
            }
        }
    }

    class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed (ActionEvent e) {
            if (e.getSource() == backToMainMenuButton) {
                manager.showMainMenu();
            }
            else
                System.out.println(e.getSource().toString());
        }
    }
}
