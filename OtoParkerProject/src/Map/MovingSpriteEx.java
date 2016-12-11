package Map;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */
import java.awt.*;
import javax.swing.JFrame;

public class MovingSpriteEx extends JFrame {

    public MovingSpriteEx() {

        initUI();
    }

    private void initUI() {

        add(new Map());

        setSize(800, 600);
        setResizable(true);
        setBackground(Color.WHITE);

        setTitle("Moving sprite");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                MovingSpriteEx ex = new MovingSpriteEx();
                ex.setVisible(true);
            }
        });
    }
}
