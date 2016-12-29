package GameObjects;

import java.awt.geom.Rectangle2D;

public class Target {

    private int width = 75;
    private int height = 100;
    private int x;
    private int y;

    public Target(int a, int b, int w, int h) {
        x = a;
        y = b;
        width = w;
        height = h;
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

    public Rectangle2D getBorders() {
        return new Rectangle2D.Double(x, y, width, height);
    }
}
