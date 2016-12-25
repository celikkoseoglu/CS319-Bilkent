package ViewManagement;

import javax.swing.*;
import java.awt.*;

public class Menu extends JPanel {

    MenuManager manager;

    public Menu(MenuManager manager) {
        this.manager = manager;
        setLayout(null);
        setBackground(Color.WHITE);
    }
}