package Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */
public class Obstacle {
    private int dx;
    private int dy;
    private int x;
    private int y;
    private Image image;
    private boolean vis;

    public Obstacle() {
        ImageIcon ii = new ImageIcon("OtoParkerProject/images/images.jpg");
        image = ii.getImage();
        x = 50;
        y = 50;
        System.out.println("Obstacle working Directory = " +
                System.getProperty("user.dir"));
        vis=true;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle2D getBounds() {
        return new Rectangle2D.Double(x, y, image.getWidth(null), image.getHeight(null));
    }




}
