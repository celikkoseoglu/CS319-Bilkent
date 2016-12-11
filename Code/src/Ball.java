import javax.swing.*;
import java.awt.*;

public class Ball extends JPanel {
    int x = 0;
    int y = 0;

    public void moveBall() {
        x = x + 1;
        y = y + 1;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.fillOval(x, y, 30, 30);
    }
}