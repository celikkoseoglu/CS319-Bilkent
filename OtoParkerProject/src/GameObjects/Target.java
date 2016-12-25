package GameObjects;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by HÜSEYİN on 24.12.2016.
 */
public class Target {

    private int width = 80;
    private int height = 100;
    private int x;
    private int y;
    private boolean vis;

    public Target(int a,int b) {
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isVisible() {
        return vis;
    }

    public void setVisible(Boolean visible) {
        vis = visible;
    }

    public Rectangle2D getBorders() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}
