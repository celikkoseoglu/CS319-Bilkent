package GameObjects;

/**
 * Created by HÜSEYİN on 11.12.2016.
 */

import javax.swing.*;
import java.awt.*;

public class Sprite {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean vis;
    protected Image image;

    public Sprite(int x, int y) {

        this.x = x;
        this.y = y;
        vis = true;
    }

    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
    }

    protected void loadImage(String imageName) {

        ImageIcon icon = new ImageIcon(imageName);
        image = icon.getImage();
    }

    public Image getImage() {

        return image;
    }

    public int getX() {

        return x;
    }

    public int getY() {

        return y;
    }

    public boolean getVisibility() {

        return vis;
    }

    public void setVisibility(Boolean visible) {

        vis = visible;
    }

    public Rectangle getBorders() {

        return new Rectangle(x, y, width, height);
    }


}
