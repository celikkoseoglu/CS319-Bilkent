import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class OtoParkerJButton extends JButton {
    public OtoParkerJButton(String text) {
        super(text);

        setForeground(Color.BLACK);
        setBackground(Color.WHITE);
        setBorder(new RoundedBorder(20));
    }

    //taken from: http://stackoverflow.com/questions/423950/rounded-swing-jbutton-using-java
    private static class RoundedBorder implements Border {

        private int radius;


        RoundedBorder(int radius) {
            this.radius = radius;
        }


        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }


        public boolean isBorderOpaque() {
            return true;
        }


        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
}
