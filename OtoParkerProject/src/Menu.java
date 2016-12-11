import javax.swing.*;
import java.awt.*;

/**
 * Created by celikkoseoglu on 11/12/2016.
 */
public class Menu extends JPanel {

    MenuManager manager;

    public Menu(MenuManager manager) {
        this.manager = manager;
        setLayout(null);
        setBackground(Color.WHITE);
    }
}
