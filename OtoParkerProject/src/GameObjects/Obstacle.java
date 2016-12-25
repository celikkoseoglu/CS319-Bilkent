package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.geom.Rectangle2D;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */
public class Obstacle {
    private int x;
    private int y;
    private Image image;
    private boolean vis;

    public Obstacle() {
        ImageIcon icon = new ImageIcon(System.getProperty("os.name").contains("Mac") ? "images/images.jpg" : "images/images.jpg");
        image = icon.getImage();
        x = 200;
        y = 200;
        vis=true;
    }

    public Obstacle(int a, int b) {
        ImageIcon icon = new ImageIcon(System.getProperty("os.name").contains("Mac") ? "images/images.jpg" : "images/images.jpg");
        image = icon.getImage();
        x = a;
        y = b;
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

    public boolean getVisibility() {
        return vis;
    }

    public void setVisiblity(Boolean visible) {
        vis = visible;
    }

    public Rectangle2D getBorders() {
        return new Rectangle2D.Double(x, y, image.getWidth(null), image.getHeight(null));
    }




}
